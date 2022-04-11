package vn.periscope.cms.adapters.persistence.contentfieldvalue

data class ContentFieldValueEntity(
    val id: Long? = null,
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
)