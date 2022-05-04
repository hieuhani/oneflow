package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant

data class BrandResponse(
    val id: Long,
    val name: String,
    val shortName: String,
    val logoId: Long,
    val countryId: Long,
    val createdAt: Instant,
    val updatedAt: Instant
)
