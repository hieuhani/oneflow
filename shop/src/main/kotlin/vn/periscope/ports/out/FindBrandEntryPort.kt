package vn.periscope.ports.out

import vn.periscope.core.domain.Brand

interface FindBrandEntryPort {
    fun find(businessId: Long): List<Brand>
}