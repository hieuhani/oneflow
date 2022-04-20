package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductEntry

interface UpdateProductEntryPort {
    fun update(id: Long, productEntry: ProductEntry): ProductEntry
}