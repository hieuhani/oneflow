package vn.periscope.ports.models

import vn.periscope.share.statics.ProductType
import vn.periscope.share.statics.ProductTaxonomy

data class ProductEntry(
    val taxonomy: ProductTaxonomy,
    val type: ProductType,
    val name: String,
    val brandId: Long?,
    val industryId: Long?,
    val categoryIds: Set<Long>?,
    val galleries: List<GalleryEntry>?,
    val attributes: List<ProductAttributeEntry>?,
)

