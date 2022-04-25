package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface CreateProductEntryPort {
    fun create(product: Product)
}