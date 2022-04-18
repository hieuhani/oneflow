package vn.periscope.ports.variant.models

import java.math.BigDecimal
import java.time.Instant

data class VariantPrice(
    val id: Long,
    val businessId: Long,
    val productId: Long,
    val variantId: Long,
    val priceListId: Long,
    val originalPrice: BigDecimal,
    val salesPrice: BigDecimal,
    val createdAt: Instant,
    val updatedAt: Instant,
)
