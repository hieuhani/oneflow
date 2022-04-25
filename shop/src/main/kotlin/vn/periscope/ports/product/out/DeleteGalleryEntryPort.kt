package vn.periscope.ports.product.out

interface DeleteGalleryEntryPort {
    fun delete(id: Long): Boolean
}