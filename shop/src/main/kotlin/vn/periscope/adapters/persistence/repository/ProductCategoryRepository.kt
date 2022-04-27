package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import vn.periscope.adapters.persistence.entity.ProductAttributeEntity
import vn.periscope.adapters.persistence.entity.ProductAttributeTable
import vn.periscope.adapters.persistence.entity.ProductCategoryEntity
import vn.periscope.adapters.persistence.entity.ProductCategoryTable
import java.time.Instant

object ProductCategoryRepository {
    private val table = ProductCategoryTable

    fun insert(entity: ProductCategoryEntity) {
        table.insert {
            it[productId] = entity.productId
            it[categoryId] = entity.categoryId
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
        }
    }

    fun batchInsert(entities: List<ProductCategoryEntity>) {
        table.batchInsert(entities) { entity ->
            this[ProductCategoryTable.productId] = entity.productId
            this[ProductCategoryTable.categoryId] = entity.categoryId
            this[ProductCategoryTable.createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
        }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = ProductCategoryEntity(
        id = resultRow[ProductCategoryTable.id].value,
        productId = resultRow[ProductCategoryTable.productId],
        categoryId = resultRow[ProductCategoryTable.categoryId],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductAttributeTable.createdAt].toEpochMilli()),
    )

    fun findByProductId(productId: Long): List<ProductCategoryEntity> {
        return table.select { table.productId eq productId }.map { fromSqlResultRow(it) }
    }
}