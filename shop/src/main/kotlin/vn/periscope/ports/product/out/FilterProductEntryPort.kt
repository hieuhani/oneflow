package vn.periscope.ports.product.out

import vn.periscope.core.domain.Product

interface FilterProductEntryPort {
    fun filter(): List<Product>
}