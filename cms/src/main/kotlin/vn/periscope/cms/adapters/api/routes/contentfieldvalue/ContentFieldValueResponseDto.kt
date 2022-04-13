package vn.periscope.cms.adapters.api.routes.contentfieldvalue

import kotlinx.serialization.Serializable

@Serializable
data class ContentFieldValueResponseDto(
    val id: Long,
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
) {

}