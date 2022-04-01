package vn.periscope.id.adapters

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import org.slf4j.event.Level
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.extentions.inject
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


        }
    }
}