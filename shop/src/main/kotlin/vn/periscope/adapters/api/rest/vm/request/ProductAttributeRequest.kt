package vn.periscope.adapters.api.rest.vm.request

@kotlinx.serialization.Serializable
data class ProductAttributeRequest(
    val name: String,
    val values: Set<String>,
)
