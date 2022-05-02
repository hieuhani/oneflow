package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProductRequest(
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<UpdateGalleryRequest>? = listOf(),
    val attributes: List<UpdateAttributeRequest>? = listOf(),
) {
    @Serializable
    data class UpdateGalleryRequest(
        val id: Long? = 0,
        val storeId: Long,
        val default: Boolean,
        val position: Int,
    )

    @Serializable
    data class UpdateAttributeRequest(
        val id: Long? = 0,
        val name: String,
        val values: Set<String>,
    )
}
