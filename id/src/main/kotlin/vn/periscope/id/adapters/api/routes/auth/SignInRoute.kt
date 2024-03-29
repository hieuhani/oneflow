package vn.periscope.id.adapters.api.routes.auth

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.id.adapters.api.routes.auth.dto.AuthResponse
import vn.periscope.id.adapters.api.routes.auth.dto.SignInRequest
import vn.periscope.id.adapters.api.routes.auth.dto.toDomainModel
import vn.periscope.id.extentions.inject
import vn.periscope.id.ports.auth.SignInUserUseCase

class SignInRoute(application: Application) {
    private val signInUserUseCase by inject<SignInUserUseCase>()
    init {
        application.routing {
            route("/auth/sign_in") {
                post {
                    val signInRequest: SignInRequest = call.receive()
                    val entry = signInUserUseCase.signInByEmail(signInRequest.toDomainModel())
                    val response = AuthResponse.fromDomainModel(entry)
                    call.respond(response)
                }
            }
        }
    }
}