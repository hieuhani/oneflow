package vn.periscope.adapters.api.routers.vm

import java.time.Instant

data class ProductAttributeResponse(
    val id:Long,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
)