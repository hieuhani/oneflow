package vn.periscope.id.ports

open class BaseError(
    open val code: Int,
    override val message: String,
    override val cause: Throwable?,
) : Throwable(message, cause) {

    companion object {
        const val INTERNAL_SERVER_ERROR_STATUS_CODE = 500_000
        const val NOT_FOUND_STATUS_CODE = 404_000
        const val UNAUTHORIZED_ERROR_STATUS_CODE = 401_000
        const val BAD_REQUEST_ERROR_STATUS_CODE = 400_000
    }

    class InternalServerError(
        cause: Throwable?,
    ) : BaseError(
        code = INTERNAL_SERVER_ERROR_STATUS_CODE,
        message = "Internal server error",
        cause = cause,
    )

    class NotFoundError(
        cause: Throwable?,
    ) : BaseError(
        code = NOT_FOUND_STATUS_CODE,
        message = "Not found error",
        cause = cause,
    )

    object UnauthorizedError : BaseError(
        code = UNAUTHORIZED_ERROR_STATUS_CODE,
        message = "Unauthorized error",
        cause = null,
    )

    object BadRequestError : BaseError(
        code = BAD_REQUEST_ERROR_STATUS_CODE,
        message = "Bad request error",
        cause = null,
    )

    abstract class FeatureError(
        code: Int,
        message: String,
        cause: Throwable?,
    ) : BaseError(code, message, cause)
}