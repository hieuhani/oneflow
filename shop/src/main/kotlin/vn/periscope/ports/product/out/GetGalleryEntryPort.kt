package vn.periscope.ports.product.out

import vn.periscope.share.statics.GalleryTargetObjectType
import vn.periscope.ports.product.models.GalleryEntry

interface GetGalleryEntryPort {
    fun findById(id: Long): GalleryEntry
    fun findByTargetObjectTypeAndTargetObjectId(
        targetObjectType: GalleryTargetObjectType, targetObjectId: Long
    ): List<GalleryEntry>
}