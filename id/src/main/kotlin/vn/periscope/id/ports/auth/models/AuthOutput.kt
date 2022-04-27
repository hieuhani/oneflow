package vn.periscope.id.ports.auth.models

import kotlinx.datetime.Instant
import vn.periscope.id.ports.session.models.SessionEntry

data class AuthOutput(
    val accessToken: String,
    val refreshToken: String,
    val session: Session,
) {
    data class Session(
        val id: String,
        val userId: Long,
        val issuedAt: Instant,
        val expiresAt: Instant,
    )
}

fun SessionEntry.toAuthSession() = AuthOutput.Session(
    id = id.toString(),
    userId,
    issuedAt,
    expiresAt,
)