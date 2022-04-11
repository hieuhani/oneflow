package vn.periscope.cms.ports.contentfieldvalue.models

data class ContentFieldValueEntry(
    val id: Long? = null,
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
)