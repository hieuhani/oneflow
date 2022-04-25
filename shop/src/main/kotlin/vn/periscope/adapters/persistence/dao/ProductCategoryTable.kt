package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.sql.Table

object ProductCategoryTable: Table(name = "product_category") {
    val product = reference("product", ProductTable)
    val category = reference("category", CategoryTable)
    override val primaryKey = PrimaryKey(product, category)
}