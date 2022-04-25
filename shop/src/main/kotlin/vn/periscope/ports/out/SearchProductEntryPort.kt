package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface SearchProductEntryPort {
    fun search(): List<Product>
}