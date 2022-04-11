package vn.periscope.cms.adapters.api.routes.dto

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry

@Serializable
data class ContentTypeResponseDto(
    val id: Long,
    val name: String,
    val machineName: String,
    val description: String,
) {
    companion object {
        fun fromDomainModel(model: ContentTypeEntry) = with(model) {
            ContentTypeResponseDto(
                id = id!!,
                name,
                machineName,
                description,
            )
        }
    }
}