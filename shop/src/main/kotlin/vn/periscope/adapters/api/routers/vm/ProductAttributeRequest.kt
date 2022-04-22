package vn.periscope.adapters.api.routers.vm

@kotlinx.serialization.Serializable
data class ProductAttributeRequest(
    val name: String,
    val values: Set<String>,
)
