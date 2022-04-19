package vn.periscope.ports.product

import vn.periscope.ports.product.models.ProductAttribute

interface UpdateProductAttributeEntryPoint {
    fun update(id: Long, productAttribute: ProductAttribute): ProductAttribute
}