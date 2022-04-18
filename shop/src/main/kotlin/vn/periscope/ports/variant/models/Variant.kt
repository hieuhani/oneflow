package vn.periscope.ports.variant.models

import vn.periscope.ports.product.models.Gallery
import java.time.Instant

data class Variant(
    val id: Long? = null,
    val businessId: Long,
    val productId: Long,
    val name: String,
    val sku: String,
    val barcode: String,
    val qrcode: String,
    val status: VariantStatus,
    val sellable: Boolean,
    val taxable: Boolean,
    val galleries: List<Gallery>,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    enum class VariantStatus {
        ACTIVE,
        INACTIVE
    }
}
