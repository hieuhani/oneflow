package vn.periscope.cms.adapters.persistence.contenttype

data class ContentTypeEntity(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
)