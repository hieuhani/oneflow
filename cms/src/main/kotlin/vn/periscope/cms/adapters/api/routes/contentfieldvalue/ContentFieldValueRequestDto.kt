package vn.periscope.cms.adapters.api.routes.contentfieldvalue

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry

@Serializable
data class ContentFieldValueRequestDto(
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
) {
    fun toDomainModel() = ContentFieldValueEntry(
        id = null,
        value,
        contentId,
        contentTypeFieldId,
    )
}