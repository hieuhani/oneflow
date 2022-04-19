package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface FilterProductEntryPort {
    fun filter(): List<Product>
}