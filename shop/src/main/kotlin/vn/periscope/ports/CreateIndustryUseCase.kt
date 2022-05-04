package vn.periscope.ports

import vn.periscope.core.domain.Industry
import vn.periscope.ports.models.IndustryEntry

interface CreateIndustryUseCase {
    suspend fun create(businessId: Long, entry: IndustryEntry): Industry
}