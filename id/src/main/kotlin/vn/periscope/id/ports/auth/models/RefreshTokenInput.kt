package vn.periscope.id.ports.auth.models

data class RefreshTokenInput(
    val refreshToken: String,
    val sessionId: String,
)