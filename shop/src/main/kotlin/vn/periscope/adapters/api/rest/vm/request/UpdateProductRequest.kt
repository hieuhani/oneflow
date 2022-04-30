package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable
import vn.periscope.share.statics.ProductType
import vn.periscope.share.statics.ProductTaxonomy

@Serializable
data class UpdateProductRequest(
    val taxonomy: ProductTaxonomy,
    val type: ProductType,
    val code: String? = "",
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<UpdateGalleryRequest>? = listOf(),
    val attributes: List<UpdateAttributeRequest>? = listOf(),
) {
    @Serializable
    data class UpdateGalleryRequest(
        val id: Long?,
        val storeId: Long,
        val default: Boolean,
        val position: Int,
    )

    @Serializable
    data class UpdateAttributeRequest(
        val id: Long?,
        val name: String,
        val values: Set<String>,
    )
}
