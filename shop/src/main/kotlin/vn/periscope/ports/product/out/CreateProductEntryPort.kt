package vn.periscope.ports.product.out

import vn.periscope.core.domain.Product

interface CreateProductEntryPort {
    fun create(product: Product): Product
}