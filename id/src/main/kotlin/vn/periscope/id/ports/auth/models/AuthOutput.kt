package vn.periscope.id.ports.auth.models

data class AuthOutput(
    val accessToken: String,
    val refreshToken: String,
)