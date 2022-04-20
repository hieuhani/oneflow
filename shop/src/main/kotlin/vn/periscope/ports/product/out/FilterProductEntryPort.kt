package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductEntry

interface FilterProductEntryPort {
    fun filter(): List<ProductEntry>
}