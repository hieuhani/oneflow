package vn.periscope.storage.adapters

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import org.slf4j.event.Level

class AppBootstrap(
    application: Application
) {
    init {
        application.apply {
            authentication {
                jwt {
                    val jwtAudience = environment.config.property("jwt.audience").getString()
                    realm = environment.config.property("jwt.realm").getString()
                    verifier(
                        JWT
                            .require(Algorithm.HMAC256("secret"))
                            .withAudience(jwtAudience)
                            .build()
                    )
                    validate { credential ->
                        if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
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