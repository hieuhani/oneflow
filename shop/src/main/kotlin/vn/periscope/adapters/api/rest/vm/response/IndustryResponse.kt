package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import vn.periscope.core.domain.Industry

@Serializable
data class IndustryResponse(
    val id: Long,
    val name: String,
    val machineName: String,
    val createdAt: Instant,
    val updateAt: Instant
) {
    companion object {
        fun fromIndustry(industry: Industry) = with(industry) {
            IndustryResponse(
                id = industry.id,
                name = industry.name,
                machineName = industry.machineName,
                createdAt = industry.createdAt,
                updateAt = industry.updatedAt
            )
        }
    }
}
