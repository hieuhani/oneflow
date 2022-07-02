package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.and
import vn.periscope.adapters.persistence.entity.ProductCategoryEntity
import vn.periscope.adapters.persistence.entity.ProductCategoryTable
import java.time.Instant

object ProductCategoryRepository {
    fun insert(_productId: Long, _categoryId: Long): ProductCategoryEntity {
        return ProductCategoryEntity.new {
            productId = _productId
            categoryId = _categoryId
            createdAt = Instant.now()
        }
    }

    fun batchInsert(_productId: Long, _categoryIds: Set<Long>) {
        _categoryIds.forEach { insert(_productId, it) }
    }

    fun delete(_productId: Long, _categoryId: Long) {
        val entity = findByFK(_productId, _categoryId) ?: return
        entity.delete()
    }

    fun batchDelete(_productId: Long, _categoryIds: Set<Long>) {
        _categoryIds.forEach { delete(_productId, it) }
    }

    fun findByFK(_productId: Long, _categoryId: Long): ProductCategoryEntity? {
        return ProductCategoryEntity.find() { (ProductCategoryTable.productId eq _productId) and (ProductCategoryTable.categoryId eq _categoryId) }
            .firstOrNull()
    }

    fun findByProductIdIn(productIds: Set<Long>): List<ProductCategoryEntity> {
        return ProductCategoryEntity.find { ProductCategoryTable.productId inList productIds }.toList()
    }

    fun findByProductId(productId: Long): List<ProductCategoryEntity> {
        return ProductCategoryEntity.find { ProductCategoryTable.productId eq productId }.toList()
    }
}