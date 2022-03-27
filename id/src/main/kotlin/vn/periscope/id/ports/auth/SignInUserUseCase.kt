package vn.periscope.id.ports.auth

import vn.periscope.id.ports.auth.models.AuthOutput
import vn.periscope.id.ports.auth.models.SignInByEmailInput

interface SignInUserUseCase {
    suspend fun signInByEmail(command: SignInByEmailInput): AuthOutput
}