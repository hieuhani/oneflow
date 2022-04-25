package vn.periscope.ports.out

interface DeleteGalleryEntryPort {
    fun delete(id: Long): Boolean
}