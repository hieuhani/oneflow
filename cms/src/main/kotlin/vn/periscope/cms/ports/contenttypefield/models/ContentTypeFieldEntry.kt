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

enum class ContentTypeFieldCardinality {
    ONE,
    MANY,
}

data class ContentTypeFieldEntry(
    val id: Long? = null,
    val label: String,
    val name: String,
    val order: Int,
    val required: Boolean,
    val type: ContentTypeFieldType,
    val cardinality: ContentTypeFieldCardinality,
    val contentTypeId: Long,
    val extraData: ContentTypeFieldExtraData? = null,
)