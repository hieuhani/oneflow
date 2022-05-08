package vn.periscope.id.adapters

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.slf4j.event.Level
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.extentions.inject
import vn.periscope.id.ports.DomainError
import vn.periscope.id.ports.auth.JWTService

class AppBootstrap(
    application: Application
) {
    private val jwtConfig by inject<JWTConfig>()
    private val jwtService by inject<JWTService>()
    init {
        application.apply {
            authentication {
                jwt {
                    realm = jwtConfig.realm
                    verifier(jwtService.getJWTVerifier())
                    validate { credential ->
                        jwtService.validate(credential)
                    }
                }
            }
            install(CallLogging) {
                level = Level.INFO
                filter { call -> call.request.path().startsWith("/") }
            }
            install(ContentNegotiation) {
                json()
            }
            install(StatusPages) {
                exception<DomainError> { call, cause ->
                    call.respond(HttpStatusCode.fromValue(cause.code / 1000), ErrorResponseDto.fromDomainError(cause))
                }
            }
        }
    }
}