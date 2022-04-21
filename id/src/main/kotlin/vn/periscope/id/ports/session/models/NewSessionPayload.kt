package vn.periscope.id.ports.session.models

import kotlinx.datetime.Instant

data class NewSessionPayload(
    val rawRefreshToken: String,
    val issuedAt: Instant,
    val userId: Long,
)