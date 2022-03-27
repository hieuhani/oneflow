package vn.periscope.id.ports.auth.models

import kotlinx.datetime.Instant

data class UserTokenPayload(
    val subject: String,
    val expiresAt: Instant,
)