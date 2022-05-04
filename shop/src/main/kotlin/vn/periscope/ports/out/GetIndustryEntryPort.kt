package vn.periscope.ports.out

import vn.periscope.core.domain.Industry

interface GetIndustryEntryPort {
    fun findById(businessId: Long, id: Long): Industry
}