package vn.periscope.cms.adapters.api.routes.dto

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.content.models.ContentEntry

@Serializable
data class CreateContentRequestDto(
    val title: String,
    val description: String,
    val contentTypeId: Long,
) {
    fun toDomainModel(userId: Long) = ContentEntry(
        id = null,
        title,
        description,
        userId,
        contentTypeId,
    )
}