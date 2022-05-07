package vn.periscope.ports

import vn.periscope.core.domain.Industry

interface FindIndustryUseCase {
    suspend fun find(businessId: Long): List<Industry>
}