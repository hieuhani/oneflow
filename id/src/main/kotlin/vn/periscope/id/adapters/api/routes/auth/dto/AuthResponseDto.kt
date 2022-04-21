package vn.periscope.id.adapters.api.routes.auth.dto

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import vn.periscope.id.ports.auth.models.AuthOutput

@Serializable
data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val session: Session,
) {
    companion object {
        fun fromDomainModel(output: AuthOutput) = AuthResponseDto(
            accessToken = output.accessToken,
            refreshToken = output.refreshToken,
            session = Session.fromDomainModel(output.session),
        )
    }

    @Serializable
    data class Session(
        val id: String,
        val userId: Long,
        val issuedAt: Instant,
        val expiresAt: Instant,
    ) {
        companion object {
            fun fromDomainModel(session: AuthOutput.Session) = with(session) {
                Session(
                    id = id,
                    userId = userId,
                    issuedAt = issuedAt,
                    expiresAt = expiresAt,
                )
            }
        }
    }
}