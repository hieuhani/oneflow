package vn.periscope.ports.product.models

import java.time.Instant

data class Product(
    val id: Long? = null,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val code: String,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val gallery: Gallery? = null,
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
