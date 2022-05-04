package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateIndustryRequest(
    val name: String,
    val machineName: String,
) {
}