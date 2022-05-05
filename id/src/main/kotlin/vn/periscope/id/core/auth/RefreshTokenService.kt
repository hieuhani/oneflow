package vn.periscope.id.core.auth

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.AuthError
import vn.periscope.id.ports.auth.GenerateUserTokenUseCase
import vn.periscope.id.ports.auth.PasswordService
import vn.periscope.id.ports.auth.RefreshTokenUseCase
import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.RefreshTokenInput
import vn.periscope.id.ports.auth.models.UserTokenPayload
import vn.periscope.id.ports.auth.models.toAuthSession
import vn.periscope.id.ports.session.GetSessionUseCase

class RefreshTokenService(
    private val getSessionUseCase: GetSessionUseCase,
    private val passwordService: PasswordService,
    private val generateUserTokenUseCase: GenerateUserTokenUseCase,
    private val transactionService: TransactionService,
) : RefreshTokenUseCase {
    override suspend fun refreshToken(input: RefreshTokenInput) = transactionService.transaction {
        val session = getSessionUseCase.getSessionById(input.sessionId)
        if (!passwordService.matches(input.refreshToken, session.refreshToken)) {
            throw AuthError.InvalidRefreshToken
        }
        val now = Clock.System.now()
        AuthOutput(
            accessToken = generateUserTokenUseCase.generate(
                UserTokenPayload(
                    subject = session.userId.toString(),
                    expiresAt = now.plus(15, DateTimeUnit.MINUTE),
                    sessionId = session.id.toString(),
                )
            ),
            refreshToken = input.refreshToken, // we keep the original refresh token
            session = session.toAuthSession(),
        )
    }
}