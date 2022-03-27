package vn.periscope.id.ports.auth.models

data class SignInByEmailInput(
    val email: String,
    val password: String,
)