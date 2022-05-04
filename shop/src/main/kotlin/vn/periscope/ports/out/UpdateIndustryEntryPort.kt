package vn.periscope.ports.out

import vn.periscope.core.domain.Industry
import vn.periscope.ports.models.IndustryEntry

interface UpdateIndustryEntryPort {
    fun update(businessId: Long, id: Long, entry: IndustryEntry): Industry
}