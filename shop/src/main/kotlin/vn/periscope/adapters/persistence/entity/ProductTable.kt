package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.ProductType
import vn.periscope.share.statics.ProductTaxonomy

object ProductTable : Table(name = "product") {
    val id = long("id").uniqueIndex()
    val nid = uuid("nid").uniqueIndex()
    val businessId = long("business_id").index()
    val taxonomy = enumerationByName("taxonomy", 32, ProductTaxonomy::class).index()
    val managementMethodology = enumerationByName("management_methodology", 32, ProductType::class).index()
    val name = varchar("name", 100).index()
    val brandId = long("brand_id").index()
    val industryId = long("industry_id").index()
    val createdAt = timestamp("created_at").index()
    val updatedAt = timestamp("updated_at").index()
    val deleted = bool("deleted").index().default(false)
}



