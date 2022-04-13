package vn.periscope.cms.adapters.api.routes.contentfieldvalue

import kotlinx.serialization.Serializable
import vn.periscope.cms.adapters.persistence.contentfieldvalue.ContentFieldValueEntity

@Serializable
data class ContentFieldValueRequestDto(
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
) {
    fun toDomainModel() = ContentFieldValueEntity(
        id = null,
        value,
        contentId,
        contentTypeFieldId,
    )
}