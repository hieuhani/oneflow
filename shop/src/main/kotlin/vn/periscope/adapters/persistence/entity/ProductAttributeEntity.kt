package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import java.util.*

data class ProductAttributeEntity(
    val id: Long,
    val nid: UUID,
    val businessId: Long,
    val productId: Long,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
)