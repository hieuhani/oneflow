package vn.periscope.ports

import vn.periscope.core.domain.Product

interface FindProductUseCase {

    suspend fun filterAndSearch(businessId: Long): List<Product>
}