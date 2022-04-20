package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductAttributeEntry

interface UpdateProductAttributeEntryPoint {
    fun update(id: Long, productAttributeEntry: ProductAttributeEntry): ProductAttributeEntry
}