package vn.periscope.cms.adapters.persistence.content

data class ContentEntity(
    val id: Long? = null,
    val title: String,
    val description: String,
    val userId: Long,
    val contentTypeId: Long,
)