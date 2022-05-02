package vn.periscope.ports.models

data class AttributeEntry(
    val id: Long? = 0,
    val name: String,
    val values: Set<String>,
)


