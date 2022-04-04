package vn.periscope.cms.ports.contenttype.models

data class ContentTypeEntry(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
)