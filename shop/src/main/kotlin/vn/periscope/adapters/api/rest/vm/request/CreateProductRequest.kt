package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable
import vn.periscope.share.statics.ProductType

@Serializable
data class CreateProductRequest(
    val type: ProductType,
    val name: String,
    val photoId: Long? = 0,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val attributes: List<CreateAttributeRequest>? = listOf(),
) {

    @Serializable
    data class CreateAttributeRequest(
        val name: String,
        val values: Set<String>,
    )

}
