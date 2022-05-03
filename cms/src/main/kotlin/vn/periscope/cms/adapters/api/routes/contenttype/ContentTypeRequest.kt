package vn.periscope.cms.adapters.api.routes.contenttype

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry

@Serializable
data class ContentTypeRequest(
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