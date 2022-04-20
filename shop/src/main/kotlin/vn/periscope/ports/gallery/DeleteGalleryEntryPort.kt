package vn.periscope.ports.gallery

interface DeleteGalleryEntryPort {
    fun delete(id: Long): Boolean
}