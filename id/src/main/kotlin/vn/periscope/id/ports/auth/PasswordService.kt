package vn.periscope.id.ports.auth

interface PasswordService {
    fun encrypt(rawPassword: String) : String
    fun matches(rawPassword: String, encryptedPassword: String) : Boolean
}