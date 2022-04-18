package vn.periscope.ports.product

import vn.periscope.ports.product.models.Product

interface GetProductEntryPoint {
    fun findById(id: Long): Product
}