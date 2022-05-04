package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CreateIndustryResponse(
    val id: Long,
    val name: String,
    val machineName: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
