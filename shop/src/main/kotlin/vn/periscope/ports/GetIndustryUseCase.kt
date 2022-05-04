package vn.periscope.ports

import vn.periscope.core.domain.Industry

interface GetIndustryUseCase {
    suspend fun findById(businessId: Long, id: Long): Industry
}