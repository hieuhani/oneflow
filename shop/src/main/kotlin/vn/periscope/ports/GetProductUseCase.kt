package vn.periscope.ports

import vn.periscope.core.domain.Product

interface GetProductUseCase {
    suspend fun findById(businessId: Long, id: Long): Product
}