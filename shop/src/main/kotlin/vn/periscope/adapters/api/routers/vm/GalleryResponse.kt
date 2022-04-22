package vn.periscope.adapters.api.routers.vm

import java.time.Instant

data class GalleryResponse(
    val id: Long,
    val businessId: Long,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val disabled: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
)
