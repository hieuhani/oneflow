package vn.periscope.ports.models

import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy

data class ProductEntry(
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val name: String,
    val brandId: Long?,
    val industryId: Long?,
    val categoryIds: Set<Long>?,
    val galleries: List<GalleryEntry>?,
    val attributes: List<ProductAttributeEntry>?,
)

