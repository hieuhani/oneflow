package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface UpdateProductEntryPort {
    fun update(id: Long, product: Product): Product
}