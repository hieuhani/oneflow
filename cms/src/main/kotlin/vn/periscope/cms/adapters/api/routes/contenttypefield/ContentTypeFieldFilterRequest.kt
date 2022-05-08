package vn.periscope.cms.adapters.api.routes.contenttypefield

import io.ktor.http.*
import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldFilterEntry

@Serializable
data class ContentTypeFieldFilterRequest(
    val limit: Int?,
    val offset: Int?,
    val contentTypeId: Long?,
) {
    companion object {
        fun fromParameters(parameters: Parameters) = ContentTypeFieldFilterRequest(
            limit = parameters["limit"]?.toIntOrNull(),
            offset = parameters["offset"]?.toIntOrNull(),
            contentTypeId = parameters["contentTypeId"]?.toLongOrNull(),
        )
    }
    fun toDomainModel() = ContentTypeFieldFilterEntry(
        limit = limit ?: 80,
        offset = offset ?: 0,
        contentTypeId = contentTypeId,
    )
}