package vn.periscope.ports.product.out

interface DeleteProductAttributeEntryPoint {
    fun delete(id: Long): Boolean
}