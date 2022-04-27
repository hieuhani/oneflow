package vn.periscope.id.core.auth

import kotlinx.datetime.*
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.*
import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.SignInByEmailInput
import vn.periscope.id.ports.auth.models.UserTokenPayload
import vn.periscope.id.ports.auth.models.toAuthSession
import vn.periscope.id.ports.session.models.NewSessionPayload
import vn.periscope.id.ports.user.GetUserEntryPort
import java.util.UUID

class SignInUserService(
    private val getUserEntryPort: GetUserEntryPort,
    private val passwordService: PasswordService,
    private val generateUserTokenUseCase: GenerateUserTokenUseCase,
    private val newSessionUseCase: NewSessionUseCase,
    private val transactionService: TransactionService,
) : SignInUserUseCase {
    override suspend fun signInByEmail(command: SignInByEmailInput) = transactionService.transaction {
        val user = getUserEntryPort.findByEmail(command.email) ?: throw AuthError.EmailNotExists
        if (!passwordService.matches(command.password, user.password)) {
            throw AuthError.WrongEmailOrPassword
        }
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