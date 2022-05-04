package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant

data class BrandEntity(
    val id: Long,
    val businessId: Long,
    val name: String,
    val shortName: String,
    val logoId: Long,
    val countryId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
)