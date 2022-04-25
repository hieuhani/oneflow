package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.VariantStatus

object VariantTable : LongIdTable(name = "product") {
    val businessId = long("business_id")
    val productId = long("product_id")
    val name = varchar("name", 100)
    val sku = varchar("code", 32)
    val barcode = varchar("barcode", 32)
    val qrcode = varchar("qrcode", 32)
    val status = enumerationByName("status", 32, VariantStatus::class)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}