package vn.periscope.adapters.api.routers.vm

import vn.periscope.adapters.persistence.product.ProductManagementMethodology
import java.time.Instant

data class ProductResponse(
    val id: Long,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val code: String,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val categoryIds: List<Long>,
    val galleries: List<GalleryResponse>,
    val attributes: List<ProductAttributeResponse>,
    val createdAt: Instant,
    val updatedAt: Instant
)
