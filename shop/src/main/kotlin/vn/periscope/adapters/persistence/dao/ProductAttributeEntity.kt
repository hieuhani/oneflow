package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductAttributeEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ProductAttributeEntity>(ProductAttributeTable)

    val nid by ProductAttributeTable.nid
    val businessId by ProductAttributeTable.businessId
    val productId by ProductAttributeTable.productId
    val name by ProductAttributeTable.name
    val values by ProductAttributeTable.values
    val createdAt by ProductAttributeTable.createdAt
    val updatedAt by ProductAttributeTable.updatedAt
}