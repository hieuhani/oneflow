package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductEntry

interface CreateProductEntryPort {
    fun create(productEntry: ProductEntry): ProductEntry
}