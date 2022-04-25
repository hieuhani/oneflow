package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class VariantEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<VariantEntity>(VariantTable)

    val businessId by VariantTable.businessId
    val productId by VariantTable.productId
    val name by VariantTable.name
    val sku by VariantTable.sku
    val barcode by VariantTable.barcode
    val qrcode by VariantTable.qrcode
    val status by VariantTable.status
    val createdAt by VariantTable.createdAt
    val updatedAt by VariantTable.updatedAt
}