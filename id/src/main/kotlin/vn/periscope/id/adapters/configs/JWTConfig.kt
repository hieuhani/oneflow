package vn.periscope.id.adapters.configs

import io.ktor.server.application.*

class JWTConfig(
    environment: ApplicationEnvironment
) {
    private val jwtConfig = environment.config.config("jwt")
    val audience = jwtConfig.property("audience").getString()
    val realm = jwtConfig.property("realm").getString()
    val secret = jwtConfig.property("secret").getString()
}