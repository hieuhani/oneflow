package vn.periscope.ports.out

import vn.periscope.core.domain.Industry

interface FindIndustryEntryPort {
    fun find(businessId: Long): List<Industry>

}