package vn.periscope.ports.models

data class ProductAttributeEntry(
    val id: Long? = 0,
    val name: String,
    val values: Set<String>,
)


