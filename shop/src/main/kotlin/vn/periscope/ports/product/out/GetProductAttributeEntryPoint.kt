package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductAttributeEntry

interface GetProductAttributeEntryPoint {
    fun findById(id: Long): ProductAttributeEntry
    fun findByProductId(id: Long): List<ProductAttributeEntry>
}