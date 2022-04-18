package vn.periscope.ports.product.models

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
) {

    enum class MediaType {
        PHOTO,
        VIDEO
    }

}