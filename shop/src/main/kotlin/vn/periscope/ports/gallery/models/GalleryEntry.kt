package vn.periscope.ports.gallery.models

import java.time.Instant

data class GalleryEntry(
    val id: Long,
    val businessId: Long,
    val targetObjectType: TargetObjectType,
    val targetObjectId: Long,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val disabled: Boolean,
    val mediaType: MediaType,
    val createdAt: Instant,
    val updatedAt: Instant
) {

    enum class MediaType {
        PHOTO, VIDEO, FILE,
    }

    enum class TargetObjectType {
        PRODUCT, VARIANT
    }
}