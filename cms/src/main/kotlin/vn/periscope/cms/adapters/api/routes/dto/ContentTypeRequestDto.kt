package vn.periscope.cms.adapters.api.routes.dto

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry

@Serializable
data class ContentTypeRequestDto(
    val name: String,
    val machineName: String,
    val description: String,
) {
    fun toDomainModel() = ContentTypeEntry(
        id = null,
        name,
        machineName,
        description,
    )
}