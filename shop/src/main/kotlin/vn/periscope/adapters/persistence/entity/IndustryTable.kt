package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object IndustryTable : LongIdTable("industry") {
    val businessId = long("business_id")
    val name = varchar("name", 255)
    val machineName = varchar("machine_name", 255).uniqueIndex()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}