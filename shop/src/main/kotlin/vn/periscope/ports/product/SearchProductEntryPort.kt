package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface SearchProductEntryPort {
    fun search(): List<Product>
}