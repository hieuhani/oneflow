package vn.periscope.id.adapters.persistence.user

import org.jetbrains.exposed.dao.id.LongIdTable


object UserTable : LongIdTable(name = "users") {
    val firstName = varchar("first_name", 100)
    val lastName = varchar("last_name", 100).nullable()
    val email = varchar("email", 320).uniqueIndex()
    val emailVerified = bool("email_verified").default(false)
    val password = varchar("password", 255)
}
