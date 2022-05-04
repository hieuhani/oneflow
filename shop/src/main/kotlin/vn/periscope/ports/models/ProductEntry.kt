package vn.periscope.ports.models

import vn.periscope.share.statics.ProductType

data class ProductEntry(
    var id:Long? = 0,
    var type: ProductType,
    var name: String,
    var photoId: Long = 0,
    var brandId: Long = 0,
    var industryId: Long = 0,
    var categoryIds: Set<Long> = setOf(),
    var attributes: List<AttributeEntry> = listOf(),
)

