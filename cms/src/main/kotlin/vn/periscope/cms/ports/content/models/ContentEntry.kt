package vn.periscope.cms.ports.content.models

data class ContentEntry(
    val id: Long? = null,
    val title: String,
    val description: String,
    val userId: Long? = null,
    val contentTypeId: Long,
)