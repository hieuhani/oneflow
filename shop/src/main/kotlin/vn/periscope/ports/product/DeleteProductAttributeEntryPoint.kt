package vn.periscope.ports.product

interface DeleteProductAttributeEntryPoint {
    fun delete(id: Long): Boolean
}