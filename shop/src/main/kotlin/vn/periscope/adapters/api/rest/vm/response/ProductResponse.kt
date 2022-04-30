package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductType
import vn.periscope.share.statics.ProductTaxonomy
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Long,
    val taxonomy: ProductTaxonomy,
    val type: ProductType,
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<GalleryResponse>? = listOf(),
    val attributes: List<ProductAttributeResponse>? = listOf(),
    val createdAt: Instant,
    val updatedAt: Instant
)
