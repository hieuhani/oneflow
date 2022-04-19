package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface CreateProductEntryPort {
    fun create(product: Product): Product
}