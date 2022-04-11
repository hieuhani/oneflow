package vn.periscope.ports.models

import java.math.BigDecimal
import java.time.Instant

data class VariantPrice(
    val id: Long,
    val businessId: Long,
    val productId: Long,
    val variantId: Long,
    val price: BigDecimal,
    val createdAt: Instant,
    val updatedAt: Instant,
)
