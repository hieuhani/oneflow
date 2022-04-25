package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy

object ProductTable : Table(name = "product") {
    val id = long("id").uniqueIndex().entityId()
    val nid = uuid("nid").uniqueIndex()
    val businessId = long("business_id")
    val taxonomy = enumerationByName("taxonomy", 32, ProductTaxonomy::class)
    val managementMethodology = enumerationByName("management_methodology", 32, ProductManagementMethodology::class)
    val name = varchar("name", 100)
    val brandId = long("brand_id")
    val industryId = long("industry_id")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}



