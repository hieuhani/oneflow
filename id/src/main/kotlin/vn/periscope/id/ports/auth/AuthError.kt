package vn.periscope.id.ports.auth

import vn.periscope.id.ports.BaseError

sealed class AuthError(
    code: Int,
    message: String,
    cause: Throwable? = null,
) : BaseError(code, message, cause) {
    companion object {
        const val EMAIL_IS_ALREADY_REGISTERED = 400_001
        const val EMAIL_IS_NOT_EXISTS = 400_002
        const val WRONG_EMAIL_OR_PASSWORD = 400_003
    }

    object EmailAlreadyRegistered : AuthError(
        EMAIL_IS_ALREADY_REGISTERED,
        "Email is already registered"
    )

    object EmailNotExists : AuthError(
        EMAIL_IS_NOT_EXISTS,
        "Email is not exists"
    )

    object WrongEmailOrPassword : AuthError(
        WRONG_EMAIL_OR_PASSWORD,
        "Wrong email or password"
    )
}