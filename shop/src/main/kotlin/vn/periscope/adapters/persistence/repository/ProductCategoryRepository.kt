package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.insertAndGetId
import vn.periscope.adapters.persistence.entity.ProductCategoryEntity
import vn.periscope.adapters.persistence.entity.ProductCategoryTable
import java.time.Instant

object ProductCategoryRepository {
    private val table = ProductCategoryTable

    fun insert(entity: ProductCategoryEntity) {
        table.insertAndGetId {
            it[productId] = entity.productId
            it[categoryId] = entity.categoryId
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
        }
    }
}