package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import java.util.*

data class AttributeEntity(
    val id: Long = 0,
    val businessId: Long,
    val ownerNid: UUID,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
)