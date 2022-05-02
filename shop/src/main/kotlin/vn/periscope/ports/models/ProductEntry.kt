package vn.periscope.ports.models

import vn.periscope.share.statics.ProductType
import vn.periscope.share.statics.ProductTaxonomy

data class ProductEntry(
    var id:Long? = 0,
    var taxonomy: ProductTaxonomy,
    var type: ProductType,
    var name: String,
    var brandId: Long = 0,
    var industryId: Long = 0,
    var categoryIds: Set<Long> = setOf(),
    var galleries: List<GalleryEntry> = listOf(),
    var attributes: List<AttributeEntry> = listOf(),
)

