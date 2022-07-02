package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductCategoryEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ProductCategoryEntity>(ProductCategoryTable)

    var productId by ProductCategoryTable.productId
    var categoryId by ProductCategoryTable.categoryId
    var createdAt by ProductCategoryTable.createdAt

}
