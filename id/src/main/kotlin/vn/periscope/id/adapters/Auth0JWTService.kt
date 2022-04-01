package vn.periscope.id.adapters

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.ports.auth.GetUserPrincipalUseCase
import vn.periscope.id.ports.auth.JWTService
import vn.periscope.id.ports.auth.models.GetUserPrincipalInput
import java.util.*

class Auth0JWTService(
    private val jwtConfig: JWTConfig,
    private val getUserPrincipalUseCase: GetUserPrincipalUseCase,
) : JWTService {
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
                    jwt.withClaim(name, Date.from(value.toJavaInstant()))
                }
            }
        }
        return jwt.sign(Algorithm.HMAC256(jwtConfig.secret))
    }

    override fun getJWTVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(jwtConfig.secret))
            .withAudience(jwtConfig.audience)
            .build()
    }

    override suspend fun validate(credential: JWTCredential): Principal? {
        if (credential.payload.audience.contains(jwtConfig.audience)) {
            val userId = credential.payload.subject.toLong()
            return getUserPrincipalUseCase.getUserPrincipal(GetUserPrincipalInput(userId))
        }
        return null
    }
}