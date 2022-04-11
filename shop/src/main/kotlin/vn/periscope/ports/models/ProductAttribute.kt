package vn.periscope.ports.models

import java.time.Instant

data class ProductAttribute(
    val id: Long,
    val businessId: Long,
    val attribute: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
)
