package vn.periscope.ports

import vn.periscope.core.domain.Product
import vn.periscope.ports.models.ProductEntry

interface CreateProductUseCase {
    suspend fun createProduct(entry: ProductEntry): Product
}