package vn.periscope.id.adapters.persistence.business

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import vn.periscope.id.adapters.persistence.user.UserTable

object BusinessTable : LongIdTable(name = "businesses") {
    val name = varchar("name", 255)
    val slug = varchar("slug", 255)
    val description = text("description")
    val address = varchar("address", 255).nullable()
    val phone = varchar("phone", 12).nullable()
    val ownerId = long("owner_id")
        .references(UserTable.id, onDelete = ReferenceOption.SET_NULL)
}