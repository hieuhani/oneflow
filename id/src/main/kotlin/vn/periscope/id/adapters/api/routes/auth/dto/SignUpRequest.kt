package vn.periscope.id.adapters.api.routes.auth.dto

import kotlinx.serialization.Serializable
import vn.periscope.id.ports.auth.models.SignUpInput

@Serializable
data class SignUpRequestDto(
    val email: String,
    val firstName: String,
    val lastName: String? = null,
    val password: String,
)

fun SignUpRequestDto.toDomainModel() = SignUpInput(
    email,
    firstName,
    lastName,
    password,
)