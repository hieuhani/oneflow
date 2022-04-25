package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object ProductCategoryTable : LongIdTable(name = "product_category") {
    val productId = long("product_id")
    val categoryId = long("category_id")
    val createdAt = timestamp("created_at")
}