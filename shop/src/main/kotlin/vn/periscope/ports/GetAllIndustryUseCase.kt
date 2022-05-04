package vn.periscope.ports

import vn.periscope.core.domain.Industry

interface GetAllIndustryUseCase {
    fun findAll(businessId: Long): List<Industry>
}