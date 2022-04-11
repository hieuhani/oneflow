package vn.periscope.ports.models

import java.time.Instant

data class Brand(
    val id: Long,
    val businessId: Long,
    val name: String,
    val gallery: Gallery,
    val disable: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
) {
}