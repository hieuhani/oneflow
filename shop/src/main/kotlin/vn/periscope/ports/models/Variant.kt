package vn.periscope.ports.models

import java.time.Instant

data class Variant(
    val id: Long,
    val businessId: Long,
    val productId: Long,
    val name: String,
    val sku: String,
    val barcode: String,
    val qrcode: String,
    val status: VariantStatus,
    val sellable: Boolean,
    val taxable: Boolean,
    val taxRateClassId: Long,
    val galleries: List<Gallery>,
    val priceList: List<VariantPrice>,
    val tags: Set<String>,
    val createdAt: Instant,
    val updateAt: Instant
) {
    enum class VariantStatus {
        ACTIVE,
        INACTIVE
    }
}
