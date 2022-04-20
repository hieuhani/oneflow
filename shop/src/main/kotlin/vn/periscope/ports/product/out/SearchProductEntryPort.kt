package vn.periscope.ports.product.out

import vn.periscope.ports.product.models.ProductEntry

interface SearchProductEntryPort {
    fun search(): List<ProductEntry>
}