package vn.periscope.id.adapters.api.routes.auth

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.id.adapters.api.routes.auth.dto.AuthResponse
import vn.periscope.id.adapters.api.routes.auth.dto.RefreshTokenRequest
import vn.periscope.id.adapters.api.routes.auth.dto.toDomainModel
import vn.periscope.id.extentions.inject
import vn.periscope.id.ports.auth.RefreshTokenUseCase

class RefreshTokenRoute(application: Application) {
    private val refreshTokenUseCase by inject<RefreshTokenUseCase>()
    init {
        application.routing {
            route("/auth/refresh_token") {
                post {
                    val refreshTokenRequest: RefreshTokenRequest = call.receive()
                    val entry = refreshTokenUseCase.refreshToken(refreshTokenRequest.toDomainModel())
                    val response = AuthResponse.fromDomainModel(entry)
                    call.respond(response)
                }
            }
        }
    }
}