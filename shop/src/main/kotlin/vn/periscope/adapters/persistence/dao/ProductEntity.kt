package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import vn.periscope.share.statics.ProductTaxonomy
import java.util.UUID

data class ProductEntity(
    val id : Long,
    val nid :UUID,
    val businessId :Long,
    val taxonomy:ProductTaxonomy.
    val managementMethodology by ProductTable.managementMethodology
    var name by ProductTable.name
    var brandId by ProductTable.brandId
    var industryId by ProductTable.industryId
    var categories by CategoryEntity via ProductCategoryTable
    var createdAt by ProductTable.createdAt
    var updatedAt by ProductTable.updatedAt
)

