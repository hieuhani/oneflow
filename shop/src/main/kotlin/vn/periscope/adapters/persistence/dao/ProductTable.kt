package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy
import java.time.Instant
import java.util.*

object ProductTable : LongIdTable(name = "product") {
    val nid = uuid("nid").uniqueIndex().default(UUID.randomUUID())
    val businessId = long("business_id")
    val taxonomy = enumerationByName("taxonomy", 32, ProductTaxonomy::class)
    val managementMethodology = enumerationByName("management_methodology", 32, ProductManagementMethodology::class)
    val name = varchar("name", 100)
    val brandId = long("brand_id")
    val industryId = long("industry_id")
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}



