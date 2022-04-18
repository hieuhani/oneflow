package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface UpdateProductEntryPoint {
    fun update(id: Long, product: Product): Product
}