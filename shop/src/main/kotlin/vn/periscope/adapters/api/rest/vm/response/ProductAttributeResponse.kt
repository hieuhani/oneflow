package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ProductAttributeResponse(
    val id: Long,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant
)