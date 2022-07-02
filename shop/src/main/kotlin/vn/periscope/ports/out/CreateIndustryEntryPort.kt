package vn.periscope.ports.out

import vn.periscope.core.domain.Industry
import vn.periscope.ports.models.IndustryEntry

interface CreateIndustryEntryPort {
    fun create(businessId: Long, entry: IndustryEntry): Industry
}