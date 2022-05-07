package vn.periscope.ports.out

import vn.periscope.core.domain.Brand

interface GetBrandEntryPort {
    fun getById(businessId: Long, id: Long): Brand
}