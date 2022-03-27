package vn.periscope.id.adapters

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import kotlinx.datetime.Instant
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.ports.auth.JWTService
import java.util.*

class Auth0JWTService(private val jwtConfig: JWTConfig) : JWTService {
    override fun sign(payload: Map<String, Any>): String {
        val jwt = JWT.create()
            .withAudience(jwtConfig.audience)
        for ((name, value) in payload) {
            when (value) {
                is String -> {
                    jwt.withClaim(name, value)
                }
                is Long -> {
                    jwt.withClaim(name, value)
                }
                is Double -> {
                    jwt.withClaim(name, value)
                }
                is Int -> {
                    jwt.withClaim(name, value)
                }
                is Instant -> {
                    jwt.withClaim(name, Date(value.epochSeconds))
                }
            }
        }
        return jwt.sign(Algorithm.HMAC256(jwtConfig.secret))
    }
}