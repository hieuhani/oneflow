package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface CreateProductEntryPoint {
    fun create(product: Product): Product
}