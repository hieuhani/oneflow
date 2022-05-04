package vn.periscope.ports

import vn.periscope.core.domain.Industry
import vn.periscope.ports.models.IndustryEntry

interface UpdateIndustryUseCase {
    suspend fun update(businessId: Long, id: Long, entry: IndustryEntry): Industry
}