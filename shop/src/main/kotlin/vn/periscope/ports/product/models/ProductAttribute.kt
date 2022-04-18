package vn.periscope.ports.product.models

import java.time.Instant

data class ProductAttribute(
    val id: Long? = null,
    val businessId: Long,
    val productId: Long,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
)
