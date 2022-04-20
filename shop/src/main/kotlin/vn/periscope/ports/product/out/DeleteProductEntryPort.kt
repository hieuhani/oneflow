package vn.periscope.ports.product.out

interface DeleteProductEntryPort {
    fun delete(id: Long): Boolean
}