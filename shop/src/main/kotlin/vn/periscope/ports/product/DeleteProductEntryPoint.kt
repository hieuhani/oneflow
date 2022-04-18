package vn.periscope.ports.product

interface DeleteProductEntryPoint {
    fun delete(id: Long): Boolean
}