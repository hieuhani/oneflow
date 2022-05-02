package vn.periscope.ports

import vn.periscope.core.domain.Product

interface FilterAndSearchProductUseCase {

    suspend fun filterAndSearch(businessId: Long): List<Product>
}