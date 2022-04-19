package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface GetProductEntryPort {
    fun findById(id: Long): Product
}