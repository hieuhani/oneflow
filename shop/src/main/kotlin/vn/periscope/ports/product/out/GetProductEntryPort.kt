package vn.periscope.ports.product.out

import vn.periscope.core.domain.Product

interface GetProductEntryPort {
    fun findById(id: Long): Product
}