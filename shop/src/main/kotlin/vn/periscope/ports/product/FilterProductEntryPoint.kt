package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface FilterProductEntryPoint {
    fun filter(): List<Product>
}