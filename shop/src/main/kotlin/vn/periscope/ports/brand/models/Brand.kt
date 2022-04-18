package vn.periscope.ports.brand.models

import vn.periscope.ports.product.models.Gallery
import kotlinx.datetime.Instant

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