package vn.periscope.ports

import vn.periscope.core.domain.Product
import vn.periscope.ports.models.ProductEntry

interface UpdateProductUseCase {
    suspend fun update(businessId: Long, id: Long, entry: ProductEntry): Product
}