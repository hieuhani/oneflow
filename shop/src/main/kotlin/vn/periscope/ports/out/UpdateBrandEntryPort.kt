package vn.periscope.ports.out

import vn.periscope.core.domain.Brand
import vn.periscope.ports.models.BrandEntry

interface UpdateBrandEntryPort {
    fun update(businessId: Long, id: Long, entry: BrandEntry): Brand
}