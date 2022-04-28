package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GalleryResponse(
    val id: Long,
    val storeId: Long,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant
)