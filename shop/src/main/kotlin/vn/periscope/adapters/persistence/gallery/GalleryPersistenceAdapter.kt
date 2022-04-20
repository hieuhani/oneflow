package vn.periscope.adapters.persistence.gallery

import vn.periscope.ports.gallery.CreateGalleryEntryPort
import vn.periscope.ports.gallery.DeleteGalleryEntryPort
import vn.periscope.ports.gallery.GetGalleryEntryPort
import vn.periscope.ports.gallery.UpdateGalleryEntryPort
import vn.periscope.ports.gallery.models.GalleryEntry
import kotlin.streams.toList

class GalleryPersistenceAdapter(
    private val galleryRepository: GalleryRepository,
) : GetGalleryEntryPort, CreateGalleryEntryPort, UpdateGalleryEntryPort, DeleteGalleryEntryPort {

    override fun findById(id: Long): GalleryEntry {
        return galleryRepository.get(id).toEntry()
    }

    override fun findByTargetObjectTypeAndTargetObjectId(
        targetObjectType: GalleryTargetObjectType, targetObjectId: Long
    ): List<GalleryEntry> {
        return galleryRepository.getByTargetObjectTypeAndTargetObjectId(targetObjectType, targetObjectId).stream()
            .map { it.toEntry() }.toList()
    }

    override fun create(gallery: GalleryEntry): GalleryEntry {
        return galleryRepository.create(gallery).toEntry()
    }

    override fun update(id: Long, gallery: GalleryEntry): GalleryEntry {
        return galleryRepository.update(id, gallery).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return galleryRepository.delete(id)
    }
}