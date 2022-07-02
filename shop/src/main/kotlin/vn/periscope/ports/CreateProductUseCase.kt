package vn.periscope.ports

import vn.periscope.ports.models.ProductEntry

interface CreateProductUseCase {
    suspend fun create(businessId: Long, entry: ProductEntry)
}