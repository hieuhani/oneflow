package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant

data class IndustryResponse(
    val id: Long,
    val name: String,
    val machineName: String,
    val createdAt: Instant,
    val updateAt: Instant
)
