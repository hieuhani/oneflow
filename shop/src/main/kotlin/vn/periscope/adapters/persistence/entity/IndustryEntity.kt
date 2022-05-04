package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import vn.periscope.core.domain.Industry

data class IndustryEntity(
    val id: Long = 0,
    val businessId: Long,
    val name: String,
    val machineName: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    fun toIndustry() = Industry(
        id, name, machineName, createdAt, updatedAt
    )
}
