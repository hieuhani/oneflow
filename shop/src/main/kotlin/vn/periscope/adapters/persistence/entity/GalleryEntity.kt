package vn.periscope.adapters.persistence.entity

import vn.periscope.share.statics.GalleryTargetObjectType
import java.time.Instant
import java.util.*

data class GalleryEntity(
    val id: Long,
    val nid: UUID,
    val businessId: Long,
    val targetObjectType: GalleryTargetObjectType,
    val targetObjectId: Long,
    val storeId: Long,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
)

