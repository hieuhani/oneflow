package vn.periscope.id.adapters.api.routes.user.dto

import kotlinx.serialization.Serializable
import vn.periscope.id.ports.user.models.UserEntry

@Serializable
data class UserResponseDto(
    val id: Long,
    val firstName: String,
    val lastName: String? = null,
    val email: String,
    val emailVerified: Boolean,
) {
    companion object {
        fun fromDomainModel(user: UserEntry) = with(user) {
            UserResponseDto(
                id = id!!,
                firstName,
                lastName,
                email,
                emailVerified,
            )
        }
    }
}