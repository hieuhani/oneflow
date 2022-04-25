package vn.periscope.ports.brand.models

import vn.periscope.ports.product.models.GalleryEntry
import kotlinx.datetime.Instant

data class Brand(
    val id: Long,
    val businessId: Long,
    val name: String,
    val galleryEntry: GalleryEntry,
    val disable: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
) {
}