package vn.periscope.ports.out

import vn.periscope.core.domain.Brand
import vn.periscope.ports.models.BrandEntry

interface CreateBrandEntryPort {
    fun create(businessId: Long, entry: BrandEntry): Brand
}