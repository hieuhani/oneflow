package vn.periscope.id.core.auth

import kotlinx.datetime.*
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.AuthError
import vn.periscope.id.ports.auth.GenerateUserTokenUseCase
import vn.periscope.id.ports.auth.PasswordService
import vn.periscope.id.ports.auth.SignUpUserUseCase
import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.SignUpInput
import vn.periscope.id.ports.auth.models.UserTokenPayload
import vn.periscope.id.ports.user.CreateUserEntryPort
import vn.periscope.id.ports.user.GetUserEntryPort
import vn.periscope.id.ports.user.models.UserEntry

class SignUpUserService(
    private val transactionService: TransactionService,
    private val createUserEntryPort: CreateUserEntryPort,
    private val getUserEntryPort: GetUserEntryPort,
    private val passwordService: PasswordService,
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
        val tokenSubject = user.id!!.toString()
        val now = Clock.System.now()
        AuthOutput(
            accessToken = generateUserTokenUseCase.generate(
                UserTokenPayload(
                    tokenSubject,
                    now.plus(60, DateTimeUnit.MINUTE)
                )
            ),
            refreshToken = generateUserTokenUseCase.generate(
                UserTokenPayload(
                    tokenSubject,
                    now.plus(1, DateTimeUnit.MONTH, TimeZone.currentSystemDefault())
                )
            )
        )
    }
}