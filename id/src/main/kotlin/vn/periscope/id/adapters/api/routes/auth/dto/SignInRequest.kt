package vn.periscope.id.adapters.api.routes.auth.dto

import kotlinx.serialization.Serializable
import vn.periscope.id.ports.auth.models.SignInByEmailInput

@Serializable
data class SignInRequestDto(
    val email: String,
    val password: String,
)

fun SignInRequestDto.toDomainModel() = SignInByEmailInput(
    email,
    password,
)