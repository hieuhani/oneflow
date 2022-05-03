package vn.periscope.cms.adapters.api.routes.contenttype

import kotlinx.serialization.Serializable
import vn.periscope.cms.adapters.api.routes.dto.PagingResponse
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry
import vn.periscope.cms.ports.resource.models.Paging

@Serializable
data class ContentTypeResponse(
    val id: Long,
    val name: String,
    val machineName: String,
    val description: String,
) {
    companion object {
        fun fromDomainModel(model: ContentTypeEntry) = with(model) {
            ContentTypeResponse(
                id = id!!,
                name,
                machineName,
                description,
            )
        }

        fun fromPagingDomainModel(pagedResource: Paging<ContentTypeEntry>) = with(pagedResource) {
            PagingResponse(
                records = records.map { fromDomainModel(it) },
                limit,
                offset,
                totalRecords,
            )
        }
    }
}