package vn.periscope.ports.product

import vn.periscope.core.domain.Product
import vn.periscope.ports.product.models.ProductEntry

interface CreateProductUseCase {
    suspend fun create(entry: ProductEntry): Product
}