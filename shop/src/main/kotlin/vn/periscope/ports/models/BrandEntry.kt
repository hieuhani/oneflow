package vn.periscope.ports.models

import kotlinx.datetime.Instant

data class BrandEntry(
    val id: Long,
    val businessId: Long,
    val name: String,
    val galleryEntry: GalleryEntry,
    val disable: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
) {
}