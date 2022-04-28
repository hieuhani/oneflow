package vn.periscope.ports.models

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy

data class ProductFilterAndSearchEntry(
    val ids: Set<Long>?,
    val categoryIds: Set<Long>?,
    val taxonomy: ProductTaxonomy?,
    val managementMethodology: ProductManagementMethodology?,
    val brandIds: Set<Long>,
    val industryIds: Set<Long>,
    val fromCreatedAt: Instant?,
    val toCreatedAt: Instant?,
    val fromUpdatedAt: Instant?,
    val toUpdatedCreatedAt: Instant?,
    val keySearch: String?,
    val pagination: PaginationEntry
)
