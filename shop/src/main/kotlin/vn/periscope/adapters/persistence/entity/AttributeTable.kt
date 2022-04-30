package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object AttributeTable : Table(name = "product_attribute") {
    val id = long("id").uniqueIndex().entityId()
    val businessId = long("business_id").index()
    val referNID = uuid("refer_nid").index()
    val name = varchar("name", 32)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
    val nid = uuid("nid").index()
}