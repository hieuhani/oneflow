package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductEntry

interface GetProductEntryPort {
    fun findById(id: Long): ProductEntry
}