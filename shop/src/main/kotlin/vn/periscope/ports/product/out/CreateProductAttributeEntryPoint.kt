package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductAttributeEntry

interface CreateProductAttributeEntryPoint {
    fun create(productAttributeEntry: ProductAttributeEntry): ProductAttributeEntry
}