package vn.periscope.id.ports.user.models

data class UserEntry(
    val id: Long? = null,
    val firstName: String,
    val lastName: String? = null,
    val email: String,
    val emailVerified: Boolean,
    val password: String,
)
