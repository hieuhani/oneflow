package vn.periscope.ports.product.out

import vn.periscope.core.domain.Product

interface SearchProductEntryPort {
    fun search(): List<Product>
}