package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface FindProductEntryPort {
    fun find(businessId: Long): List<Product>
}