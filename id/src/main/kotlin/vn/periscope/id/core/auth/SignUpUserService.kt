package vn.periscope.id.core.auth

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.*
import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.SignUpInput
import vn.periscope.id.ports.auth.models.UserTokenPayload
import vn.periscope.id.ports.auth.models.toAuthSession
import vn.periscope.id.ports.session.models.NewSessionPayload
import vn.periscope.id.ports.user.CreateUserEntryPort
import vn.periscope.id.ports.user.GetUserEntryPort
import vn.periscope.id.ports.user.models.UserEntry
import java.util.*

class SignUpUserService(
    private val transactionService: TransactionService,
    private val createUserEntryPort: CreateUserEntryPort,
    private val getUserEntryPort: GetUserEntryPort,
    private val passwordService: PasswordService,
    private val newSessionUseCase: NewSessionUseCase,
    private val generateUserTokenUseCase: GenerateUserTokenUseCase,
) : SignUpUserUseCase {
    override suspend fun signUp(command: SignUpInput) = transactionService.transaction {
        getUserEntryPort.findByEmail(command.email).takeIf { it != null }?.apply {
            throw AuthError.EmailAlreadyRegistered
        }

        val user = createUserEntryPort.createUser(
            UserEntry(
                email = command.email,
                firstName = command.firstName,
                lastName = command.lastName,
                password = passwordService.encrypt(command.password),
                emailVerified = false,
            )
        )
        val now = Clock.System.now()
        val refreshToken = UUID.randomUUID().toString()
        val session = newSessionUseCase.newSession(
            NewSessionPayload(
                rawRefreshToken = refreshToken,
                userId = user.id!!,
                issuedAt = now,
            )
        )

        AuthOutput(
            accessToken = generateUserTokenUseCase.generate(
                UserTokenPayload(
                    subject = user.id.toString(),
                    expiresAt = now.plus(15, DateTimeUnit.MINUTE),
                    sessionId = session.id.toString(),
                )
            ),
            refreshToken = refreshToken,
            session = session.toAuthSession(),
        )
    }
}