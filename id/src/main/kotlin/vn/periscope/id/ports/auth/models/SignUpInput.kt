package vn.periscope.id.ports.auth.models

data class SignUpInput(
    val email: String,
    val firstName: String,
    val lastName: String? = null,
    val password: String,
)