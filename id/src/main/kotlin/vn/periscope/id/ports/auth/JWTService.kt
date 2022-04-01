package vn.periscope.id.ports.auth

import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

interface JWTService {
    fun sign(payload: Map<String, Any>): String
    fun getJWTVerifier(): JWTVerifier
    suspend fun validate(credential: JWTCredential): Principal?
}