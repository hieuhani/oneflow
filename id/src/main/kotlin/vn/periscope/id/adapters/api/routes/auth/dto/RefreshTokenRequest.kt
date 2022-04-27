package vn.periscope.id.adapters.api.routes.auth.dto

import kotlinx.serialization.Serializable
import vn.periscope.id.ports.auth.models.RefreshTokenInput

@Serializable
data class RefreshTokenRequest(
    val refreshToken: String,
    val sessionId: String,
)

fun RefreshTokenRequest.toDomainModel() = RefreshTokenInput(
    refreshToken,
    sessionId,
)