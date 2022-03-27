package vn.periscope.id.core.auth

import vn.periscope.id.ports.auth.GenerateUserTokenUseCase
import vn.periscope.id.ports.auth.JWTService
import vn.periscope.id.ports.auth.models.UserTokenPayload

class GenerateUserTokenService(
    private val jwtService: JWTService,
) : GenerateUserTokenUseCase {
    override fun generate(payload: UserTokenPayload): String {
        return jwtService.sign(
            hashMapOf(
                "sub" to payload.subject,
                "exp" to payload.expiresAt,
            )
        )
    }
}