package vn.periscope.cms.ports.contenttypefield.models

enum class ContentTypeFieldType {
    UNSPECIFIED,
    TEXT,
    BOOLEAN,
    DATETIME,
    LINK,
    EMAIL,
    GEO_LOCATION,
    TAXONOMY_TERM,
    FILE,
}

data class ContentTypeFieldEntry(
    val id: Long? = null,
    val label: String,
    val name: String,
    val order: Int,
    val required: Boolean,
    val type: ContentTypeFieldType,
    val contentTypeId: Long,
)