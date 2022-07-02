package vn.periscope.ports

import vn.periscope.ports.models.ProductEntry

interface GetProductUseCase {
    suspend fun getById(businessId: Long, id: Long): ProductEntry
}