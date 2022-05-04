package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductType
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Long,
    val type: ProductType,
    val name: String,
    val photoId: Long,
    val brandId: Long,
    val industryId: Long,
    val categoryIds: Set<Long>,
    val attributes: List<ProductAttributeResponse>,
    val createdAt: Instant,
    val updatedAt: Instant
)
