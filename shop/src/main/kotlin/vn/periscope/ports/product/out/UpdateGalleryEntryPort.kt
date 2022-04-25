package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.GalleryEntry

interface UpdateGalleryEntryPort {
    fun update(id: Long, gallery: GalleryEntry): GalleryEntry
}