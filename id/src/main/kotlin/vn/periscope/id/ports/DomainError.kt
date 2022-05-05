package vn.periscope.id.ports

open class DomainError(
    open val code: Int,
    override val message: String,
    override val cause: Throwable?,
) : RuntimeException(message, cause) {
    class InternalServerError(
        cause: Throwable?,
    ) : DomainError(
        code = 500_000,
        message = "Internal server error",
        cause = cause,
    )

    class NotFoundError(
        cause: Throwable?,
    ) : DomainError(
        code = 404_000,
        message = "Not found error",
        cause = cause,
    )

    object UnauthorizedError : DomainError(
        code = 401_000,
        message = "Unauthorized error",
        cause = null,
    )

    object BadRequestError : DomainError(
        code = 400_000,
        message = "Bad request error",
        cause = null,
    )

    abstract class FeatureError(
        code: Int,
        message: String,
        cause: Throwable?,
    ) : DomainError(code, message, cause)
}