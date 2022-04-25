package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.GalleryEntry

interface CreateGalleryEntryPort {
    fun create(gallery: GalleryEntry): GalleryEntry
}