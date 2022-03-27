package vn.periscope.id.core.auth

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import vn.periscope.id.ports.auth.PasswordService

class Argon2PasswordService : PasswordService {
    private val argon2PasswordEncoder = Argon2PasswordEncoder()

    override fun encrypt(rawPassword: String): String {
        return argon2PasswordEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: String, encryptedPassword: String): Boolean {
        return argon2PasswordEncoder.matches(rawPassword, encryptedPassword)
    }
}