package vn.periscope.id.ports.auth

import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.RefreshTokenInput

interface RefreshTokenUseCase {
    suspend fun refreshToken(input: RefreshTokenInput): AuthOutput
}