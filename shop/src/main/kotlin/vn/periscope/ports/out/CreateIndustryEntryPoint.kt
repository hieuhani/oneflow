package vn.periscope.ports.out

import vn.periscope.core.domain.Industry

interface CreateIndustryEntryPoint {
    fun create(businessId: Long, industry: Industry): Industry
}