package vn.periscope.ports

import vn.periscope.core.domain.Product
import vn.periscope.ports.models.ProductEntry

interface UpdateProductUseCase {
    suspend fun update(productEntry: ProductEntry, product: Product)
}