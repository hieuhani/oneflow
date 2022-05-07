package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable


@Serializable
data class CreateBrandRequest(
    val name: String,
    val logoId: Long? = 0,
    val countryId: Long? = 0
)