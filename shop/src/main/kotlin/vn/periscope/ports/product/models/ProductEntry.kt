package vn.periscope.ports.product.models

import vn.periscope.ports.gallery.models.GalleryEntry
import java.time.Instant

data class ProductEntry(
    val id: Long? = null,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val code: String,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val categoryIds: List<Long>,
    val galleries: List<GalleryEntry>,
    val attributes: List<ProductAttributeEntry>,
    val createdAt: Instant,
    val updatedAt: Instant
) {

    enum class ProductTaxonomy(description: String) {
        SERVICE(""),
        PHYSICAL(""),
        DIGITAL_GOODS("")
    }

    enum class ProductManagementMethodology(description: String) {
        SERIAL("SERIAL"),
        LOTS_DATE("LOTS_DATE"),
        NORMAL("NORMAL")
    }
}
