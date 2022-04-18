package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface SearchProductEntryPoint {
    fun search(): List<Product>
}