package vn.periscope.ports.product

import vn.periscope.core.domain.Product

interface GetProductUseCase{
    suspend fun findById(id: Long): Product
}