package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface CreateProductEntryPort {
    fun insert(businessId: Long, product: Product)
}