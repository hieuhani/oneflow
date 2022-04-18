package vn.periscope.id.adapters

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.ports.auth.JWTService
import vn.periscope.id.ports.auth.models.UserPrincipal
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

class Auth0JWTService(
    private val jwtConfig: JWTConfig,
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
        return jwt.sign(Algorithm.RSA256(null, createRSAPrivateKey(jwtConfig.privateKey!!)))
    }

    override fun getJWTVerifier(): JWTVerifier {
        return JWT
            .require(Algorithm.RSA256(createRSAPublicKey(jwtConfig.publicKey), null))
            .withAudience(jwtConfig.audience)
            .build()
    }

    override suspend fun validate(credential: JWTCredential): Principal? {
        if (credential.payload.audience.contains(jwtConfig.audience)) {
            val userId = credential.payload.subject.toLong()
            return UserPrincipal(userId)
        }
        return null
    }

    companion object {
        fun createRSAPrivateKey(privateKey: String): RSAPrivateKey {
            val keyFactory = KeyFactory.getInstance("RSA")
            val pkcs8EncodedKeySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey))
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec) as RSAPrivateKey
        }
        fun createRSAPublicKey(publicKey: String): RSAPublicKey {
            val keyFactory = KeyFactory.getInstance("RSA")
            val x509EncodedKeySpec = X509EncodedKeySpec(Base64.getDecoder().decode(publicKey))
            return keyFactory.generatePublic(x509EncodedKeySpec) as RSAPublicKey
        }
    }
}