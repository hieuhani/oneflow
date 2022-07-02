package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object AttributeTable : LongIdTable(name = "attribute") {
    val ownerNid = uuid("owner_nid").index()
    val name = varchar("name", 32)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}