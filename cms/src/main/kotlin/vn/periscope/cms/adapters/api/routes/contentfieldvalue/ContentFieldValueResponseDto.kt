package vn.periscope.cms.adapters.api.routes.contentfieldvalue

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry

@Serializable
data class ContentFieldValueResponseDto(
    val id: Long,
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
) {
    companion object {
        fun fromDomainModel(model: ContentFieldValueEntry) = with(model) {
            ContentFieldValueResponseDto(
                id = id!!,
                value,
                contentId,
                contentTypeFieldId,
            )
        }
    }
}