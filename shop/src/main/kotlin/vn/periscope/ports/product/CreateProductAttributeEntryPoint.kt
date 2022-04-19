package vn.periscope.ports.product

import vn.periscope.ports.product.models.ProductAttribute

interface CreateProductAttributeEntryPoint {
    fun create(productAttribute: ProductAttribute): ProductAttribute
}