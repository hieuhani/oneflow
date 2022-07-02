package vn.periscope.ports.out

import vn.periscope.core.domain.Product
import vn.periscope.ports.models.ProductEntry

interface UpdateProductEntryPort {
    fun update(businessId: Long, id: Long, entry: ProductEntry): Product
}