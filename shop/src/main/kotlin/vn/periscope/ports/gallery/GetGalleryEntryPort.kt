package vn.periscope.ports.gallery

import vn.periscope.adapters.persistence.gallery.GalleryTargetObjectType
import vn.periscope.ports.gallery.models.GalleryEntry

interface GetGalleryEntryPort {
    fun findById(id: Long): GalleryEntry
    fun findByTargetObjectTypeAndTargetObjectId(
        targetObjectType: GalleryTargetObjectType, targetObjectId: Long
    ): List<GalleryEntry>
}