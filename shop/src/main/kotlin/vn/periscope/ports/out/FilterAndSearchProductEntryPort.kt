package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface FilterAndSearchProductEntryPort {
    fun filter(businessId: Long): List<Product>
}