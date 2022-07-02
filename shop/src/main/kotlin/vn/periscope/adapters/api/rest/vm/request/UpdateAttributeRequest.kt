package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAttributeRequest(
    val id: Long? = 0,
    val name: String,
    val values: Set<String>,
)
