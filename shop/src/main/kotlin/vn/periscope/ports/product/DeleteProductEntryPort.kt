package vn.periscope.ports.product

interface DeleteProductEntryPort {
    fun delete(id: Long): Boolean
}