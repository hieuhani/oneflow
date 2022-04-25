package vn.periscope.ports.out

interface DeleteProductAttributeEntryPoint {
    fun delete(id: Long): Boolean
}