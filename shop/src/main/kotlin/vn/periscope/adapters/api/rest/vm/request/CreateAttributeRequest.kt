package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable
import vn.periscope.ports.models.AttributeEntry

@Serializable
data class CreateAttributeRequest(
    val name: String,
    val values: Set<String>,
)
