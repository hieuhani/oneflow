package vn.periscope.ports.gallery

import vn.periscope.ports.gallery.models.GalleryEntry

interface CreateGalleryEntryPort {
    fun create(gallery: GalleryEntry): GalleryEntry
}