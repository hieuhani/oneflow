package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import vn.periscope.share.statics.AttributeReferType
import java.util.*

data class AttributeEntity(
    val id: Long,
    val businessId: Long,
    val referType: AttributeReferType,
    val referId: Long,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val nid: UUID,
)