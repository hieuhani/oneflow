package vn.periscope.adapters.api.rest.vm.request


data class CreateBrandRequest(
    val name: String,
    val shortName: String,
    val logoId: Long? = 0,
    val countryId: Long? = 0
)