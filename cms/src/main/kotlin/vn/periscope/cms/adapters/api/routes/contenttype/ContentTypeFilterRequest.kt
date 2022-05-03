package vn.periscope.cms.adapters.api.routes.contenttype

import io.ktor.http.*
import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttype.models.ContentTypeFilterEntry

@Serializable
data class ContentTypeFilterRequest(
    val limit: Int?,
    val offset: Int?,
) {
    companion object {
        fun fromParameters(parameters: Parameters) = ContentTypeFilterRequest(
            limit = parameters["limit"]?.toIntOrNull(),
            offset = parameters["offset"]?.toIntOrNull(),
        )
    }

    fun toDomainModel() = ContentTypeFilterEntry(
        limit = limit ?: 80,
        offset = offset ?: 0,
    )
}