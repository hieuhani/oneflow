package vn.periscope.ports.models

import kotlinx.datetime.Instant
import vn.periscope.share.statics.GalleryTargetObjectType
import java.util.UUID

data class GalleryEntry(
    val id: Long,
    val nid: UUID,
    val businessId: Long,
    val targetObjectType: GalleryTargetObjectType,
    val targetObjectId: Long,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant
)