package vn.periscope.id.adapters.api.routes.auth.dto

import kotlinx.serialization.Serializable
import vn.periscope.id.ports.auth.models.AuthOutput

@Serializable
data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun fromDomainModel(output: AuthOutput) = AuthResponseDto(
            accessToken = output.accessToken,
            refreshToken = output.refreshToken,
        )
    }
}