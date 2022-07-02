package vn.periscope.ports.out

import vn.periscope.ports.models.ProductEntry

interface CreateProductEntryPort {
    fun create(businessId: Long, entry: ProductEntry)
}