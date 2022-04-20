package vn.periscope.adapters.persistence.productattribute

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object ProductAttributeTable : LongIdTable(name = "product_attribute") {
    val businessId = long("business_id")
    val productId = long("product_id")
    val name = varchar("name", 32)
    val values = varchar("values", 512)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}