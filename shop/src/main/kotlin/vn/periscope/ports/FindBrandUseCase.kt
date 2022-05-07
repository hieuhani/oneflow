package vn.periscope.ports

import vn.periscope.core.domain.Brand

interface FindBrandUseCase {
    suspend fun find(businessId: Long): List<Brand>
}