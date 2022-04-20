package vn.periscope.adapters.persistence.product

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object ProductTable : LongIdTable(name = "product") {
    val businessId = long("business_id")
    val taxonomy = enumerationByName("taxonomy", 32, ProductTaxonomy::class)
    val managementMethodology = enumerationByName("management_methodology", 32, ProductManagementMethodology::class)
    val code = varchar("code", 16)
    val name = varchar("name", 100)
    val brandId = long("brand_id").default(0)
    val industryId = long("industry_id").default(0)
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}



