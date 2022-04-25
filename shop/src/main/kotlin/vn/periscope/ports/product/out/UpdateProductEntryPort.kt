package vn.periscope.ports.product.out

import vn.periscope.core.domain.Product

interface UpdateProductEntryPort {
    fun update(id: Long, product: Product): Product
}