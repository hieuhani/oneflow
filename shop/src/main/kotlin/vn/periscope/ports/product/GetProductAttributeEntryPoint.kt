package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface GetProductAttributeEntryPoint {
    fun findById(id: Long): Product
    fun findByProductId(id: Long): Product
}