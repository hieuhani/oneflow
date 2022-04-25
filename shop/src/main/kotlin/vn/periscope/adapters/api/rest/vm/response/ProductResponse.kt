package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Long,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<GalleryResponse>? = listOf(),
    val attributes: List<ProductAttributeResponse>? = listOf(),
    val createdAt: Instant,
    val updatedAt: Instant
)
