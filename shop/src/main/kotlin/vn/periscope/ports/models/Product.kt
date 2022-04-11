package vn.periscope.ports.models

import java.time.Instant

data class Product(
    val id: Long,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ManagementMethodology,
    val name: String,
    val branchId: Long,
    val industryId: Long,
    val gallery: Gallery,
    val createdAt: Instant,
    val updateAt: Instant
) {

    enum class ProductTaxonomy(description: String) {
        SERVICE(""),
        PHYSICAL(""),
        DIGITAL_GOODS("")
    }

    enum class ManagementMethodology(description: String) {
        SERIAL("SERIAL"),
        LOTS_DATE("LOTS_DATE"),
        NORMAL("NORMAL")
    }


    fun verify() {

    }

    fun verifyManagementMethodology() {

    }
}
