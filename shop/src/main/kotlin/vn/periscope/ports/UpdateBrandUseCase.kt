package vn.periscope.ports

import vn.periscope.core.domain.Brand
import vn.periscope.ports.models.BrandEntry

interface UpdateBrandUseCase {
   suspend fun update(businessId: Long, id: Long, entry: BrandEntry): Brand
}