package vn.periscope.id.ports.auth

import vn.periscope.id.ports.DomainError

sealed class AuthError(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : DomainError(code, message, cause) {
    object EmailAlreadyRegistered : AuthError(
        400_001,
        "Email is already registered"
    )

    object EmailNotExists : AuthError(
        400_002,
        "Email is not exists"
    )

    object WrongEmailOrPassword : AuthError(
        400_003,
        "Wrong email or password"
    )
}