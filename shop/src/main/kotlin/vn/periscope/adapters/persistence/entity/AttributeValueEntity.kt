package vn.periscope.adapters.persistence.entity

import java.util.UUID

data class AttributeValueEntity(
    val attributeNID: UUID,
    val value: String,
)
