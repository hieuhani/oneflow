package vn.periscope.id.adapters.api.routes.auth

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.id.adapters.api.routes.auth.dto.AuthResponse
import vn.periscope.id.adapters.api.routes.auth.dto.SignUpRequest
import vn.periscope.id.adapters.api.routes.auth.dto.toDomainModel
import vn.periscope.id.extentions.inject
import vn.periscope.id.ports.auth.SignUpUserUseCase

class SignUpRoute(application: Application) {
    private val signUpUserUseCase by inject<SignUpUserUseCase>()
    init {
        application.routing {
            route("/auth/sign_up") {
                post {
                    val signUpRequest: SignUpRequest = call.receive()
                    val entry = signUpUserUseCase.signUp(signUpRequest.toDomainModel())
                    val response = AuthResponse.fromDomainModel(entry)
                    call.respond(response)
                }
            }
        }
    }
}