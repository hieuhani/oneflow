package vn.periscope.ports

import vn.periscope.core.domain.Brand
import vn.periscope.ports.models.BrandEntry

interface CreateBrandUseCase {
    suspend fun create(businessId: Long, entry: BrandEntry): Brand
}