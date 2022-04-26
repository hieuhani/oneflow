package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
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
            this[ProductAttributeTable.createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
        }
    }
}