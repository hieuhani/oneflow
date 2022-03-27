package vn.periscope.id.ports.auth

interface JWTService {
    fun sign(payload: Map<String, Any>): String
}