package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ObjectReferenceType
import java.util.UUID

data class AttributeEntity(
    val id: Long,
    val businessId: Long,
    val referNID: UUID,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val nid: UUID,
)