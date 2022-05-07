package vn.periscope.ports

import vn.periscope.core.domain.Brand

interface GetBrandUseCase {
    suspend fun getById(businessId: Long, id: Long): Brand
}