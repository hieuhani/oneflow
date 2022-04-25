package vn.periscope.ports.product.out

interface DeleteVariantEntryPoint {
    fun delete(id: Long): Boolean
}