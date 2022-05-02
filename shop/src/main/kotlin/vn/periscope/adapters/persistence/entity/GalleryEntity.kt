package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import vn.periscope.share.statics.GalleryReferType
import java.util.*

data class GalleryEntity(
    val id: Long,
    val businessId: Long,
    val referType: GalleryReferType,
    val referId: Long,
    val storeId: Long,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val nid: UUID,
)

