package vn.periscope.id.core.auth

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.AuthError
import vn.periscope.id.ports.auth.GenerateUserTokenUseCase
import vn.periscope.id.ports.auth.PasswordService
import vn.periscope.id.ports.auth.SignInUserUseCase
import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.SignInByEmailInput
import vn.periscope.id.ports.auth.models.UserTokenPayload
import vn.periscope.id.ports.user.GetUserEntryPort

class SignInUserService(
    private val getUserEntryPort: GetUserEntryPort,
    private val passwordService: PasswordService,
    private val generateUserTokenUseCase: GenerateUserTokenUseCase,
    private val transactionService: TransactionService,
) : SignInUserUseCase {
    override suspend fun signInByEmail(command: SignInByEmailInput) = transactionService.transaction {
        val user = getUserEntryPort.findByEmail(command.email) ?: throw AuthError.EmailNotExists
        if (!passwordService.matches(command.password, user.password)) {
            throw AuthError.WrongEmailOrPassword
        }
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