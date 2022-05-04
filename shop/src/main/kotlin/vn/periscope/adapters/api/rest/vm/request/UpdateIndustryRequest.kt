package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateIndustryRequest(
    val name: String,
    val machineName: String,
) {
}