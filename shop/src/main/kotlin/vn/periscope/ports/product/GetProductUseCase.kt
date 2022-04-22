package vn.periscope.ports.product

import vn.periscope.ports.product.models.ProductEntry

interface GetProductUseCase{
    suspend fun findById(id: Long): ProductEntry
}