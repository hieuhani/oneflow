package vn.periscope.core.domain

import vn.periscope.ports.gallery.models.GalleryEntry
import vn.periscope.ports.product.models.ProductAttributeEntry
import vn.periscope.ports.product.models.ProductEntry
import java.time.Instant

data class Product(
    val id: Long? = null,
    val businessId: Long,
    val taxonomy: ProductEntry.Taxonomy,
    val managementMethodology: ProductEntry.ManagementMethodology,
    val code: String,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val categoryIds: List<Long>,
    val galleries: List<GalleryEntry>,
    val attributes: List<ProductAttributeEntry>,
    val createdAt: Instant,
    val updatedAt: Instant
){

}
