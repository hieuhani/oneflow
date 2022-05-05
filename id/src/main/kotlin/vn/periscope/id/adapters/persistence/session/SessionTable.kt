package vn.periscope.id.adapters.persistence.session

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import vn.periscope.id.adapters.persistence.user.UserTable

object SessionTable : UUIDTable(name = "sessions") {
    val userId = long("user_id")
        .references(UserTable.id, onDelete = ReferenceOption.CASCADE)
    val issuedAt = timestamp("issued_at").defaultExpression(CurrentTimestamp())
    val expiresAt = timestamp("expires_at")
    val refreshToken = varchar("refresh_token", 255)
}