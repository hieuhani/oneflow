package vn.periscope.adapters.persistence.dao

import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy
import java.time.Instant
import java.util.*

data class ProductEntity(
    val id: Long,
    val nid: UUID,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
)
