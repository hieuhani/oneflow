package vn.periscope.adapters.api.routers.vm

import vn.periscope.ports.product.models.ProductEntry

@kotlinx.serialization.Serializable
data class CreateProductRequest(
    val taxonomy: ProductEntry.Taxonomy,
    val managementMethodology: ProductEntry.ManagementMethodology,
    val code: String,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val categoryIds: List<Long>,
    val galleries: List<GalleryRequest>,
    val attributes: List<ProductAttributeRequest>,
) {
}
