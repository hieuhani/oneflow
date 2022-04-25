package vn.periscope.ports.product.models

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
    val galleries: List<GalleryEntry>,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    enum class VariantStatus {
        ACTIVE,
        INACTIVE
    }
}
