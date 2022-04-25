package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.util.*

object ProductAttributeTable : LongIdTable(name = "product_attribute") {

    val nid = CategoryTable.uuid("nid").default(UUID.randomUUID())
    val businessId = long("business_id")
    val productId = long("product_id")
    val name = varchar("name", 32)
    val values = varchar("values", 512)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}