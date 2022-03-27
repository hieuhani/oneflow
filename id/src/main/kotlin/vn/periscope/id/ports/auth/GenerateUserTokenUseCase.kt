package vn.periscope.id.ports.auth

import vn.periscope.id.ports.auth.models.UserTokenPayload

interface GenerateUserTokenUseCase {
    fun generate(payload: UserTokenPayload) : String
}