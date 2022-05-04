package vn.periscope.ports.models

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductType

data class ProductFilterAndSearchEntry(
    val ids: Set<Long>?,
    val categoryIds: Set<Long>?,
    val taxonomy: ProductType?,
    val managementMethodology: ProductType?,
    val brandIds: Set<Long>,
    val industryIds: Set<Long>,
    val fromCreatedAt: Instant?,
    val toCreatedAt: Instant?,
    val fromUpdatedAt: Instant?,
    val toUpdatedCreatedAt: Instant?,
    val keySearch: String?,
    val pagination: PaginationEntry
)
