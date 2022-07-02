package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.ProductType

object ProductTable : LongIdTable(name = "product") {
    val businessId = long("business_id").index()
    val type = enumerationByName("taxonomy", 32, ProductType::class).index()
    val name = varchar("name", 100).index()
    val photoId = long("photo_id").index()
    val brandId = long("brand_id").index()
    val industryId = long("industry_id").index()
    val price = double("price").index()
    val createdAt = timestamp("created_at").index()
    val updatedAt = timestamp("updated_at").index()
    val deleted = bool("deleted").index().default(false)
}



