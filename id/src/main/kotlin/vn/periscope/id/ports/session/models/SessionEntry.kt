package vn.periscope.id.ports.session.models

import kotlinx.datetime.Instant
import java.util.UUID

data class SessionEntry(
    val id: UUID? = null,
    val userId: Long,
    val issuedAt: Instant,
    val expiresAt: Instant,
    val refreshToken: String,
)