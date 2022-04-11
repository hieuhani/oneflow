package vn.periscope.ports.models

import java.time.Instant

data class Gallery(
    val id: Long,
    val businessId: Long,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val disabled: Boolean,
    val mediaType: MediaType,
    val createdAt: Instant,
    val updatedAt: Instant
) {

    enum class MediaType {
        PHOTO,
        VIDEO
    }

}