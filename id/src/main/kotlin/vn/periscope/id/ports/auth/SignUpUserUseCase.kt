package vn.periscope.id.ports.auth

import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.SignUpInput

interface SignUpUserUseCase {
    suspend fun signUp(command: SignUpInput): AuthOutput
}