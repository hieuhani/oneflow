package vn.periscope.id.adapters

import kotlinx.serialization.Serializable
import vn.periscope.id.ports.DomainError

@Serializable
data class ErrorResponseDto(
    val code: Int,
    val message: String,
) {
    companion object {
        fun fromDomainError(error: DomainError) = ErrorResponseDto(
            code = error.code,
            message = error.message,
        )
    }
}