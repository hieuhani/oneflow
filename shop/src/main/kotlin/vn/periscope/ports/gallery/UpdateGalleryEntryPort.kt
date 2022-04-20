package vn.periscope.ports.gallery

import vn.periscope.ports.gallery.models.GalleryEntry

interface UpdateGalleryEntryPort {
    fun update(id: Long, gallery: GalleryEntry): GalleryEntry
}