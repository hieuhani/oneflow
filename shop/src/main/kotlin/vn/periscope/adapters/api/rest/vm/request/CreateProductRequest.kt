package vn.periscope.adapters.api.rest.vm.request

import vn.periscope.share.statics.ProductType
import vn.periscope.share.statics.ProductTaxonomy
import kotlinx.serialization.Serializable

@Serializable
data class CreateProductRequest(
    val taxonomy: ProductTaxonomy,
    val type: ProductType,
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<CreateGalleryRequest>? = listOf(),
    val attributes: List<CreateAttributeRequest>? = listOf(),
){
    @Serializable
    data class CreateGalleryRequest(
        val storeId: Long,
        val default: Boolean,
        val position: Int,
    )
    @Serializable
    data class CreateAttributeRequest(
        val name: String,
        val values: Set<String>,
    )

}
