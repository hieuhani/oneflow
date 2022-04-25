package vn.periscope.adapters.api.rest.vm.request

import vn.periscope.share.statics.ProductTaxonomy

@kotlinx.serialization.Serializable
data class CreateProductRequest(
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val code: String? = "",
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<GalleryRequest>? = listOf(),
    val attributes: List<ProductAttributeRequest>? = listOf(),
)
