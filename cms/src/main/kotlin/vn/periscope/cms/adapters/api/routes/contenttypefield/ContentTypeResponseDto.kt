package vn.periscope.cms.adapters.api.routes.contenttypefield

import kotlinx.serialization.Serializable
import vn.periscope.cms.adapters.api.routes.dto.PagingResponse
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldCardinality
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldExtraData
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldType
import vn.periscope.cms.ports.resource.models.Paging

@Serializable
data class ContentTypeFieldResponse(
    val id: Long,
    val label: String,
    val name: String,
    val order: Int,
    val required: Boolean,
    val type: ContentTypeFieldType,
    val cardinality: ContentTypeFieldCardinality,
    val contentTypeId: Long,
    val extraData: ContentTypeFieldExtraData?,
) {
    companion object {
        fun fromDomainModel(model: ContentTypeFieldEntry) = with(model) {
            ContentTypeFieldResponse(
                id = id!!,
                label,
                name,
                order,
                required,
                type,
                cardinality,
                contentTypeId,
                extraData,
            )
        }

        fun fromPagingDomainModel(pagedResource: Paging<ContentTypeFieldEntry>) = with(pagedResource) {
            PagingResponse(
                records = records.map { fromDomainModel(it) },
                limit,
                offset,
                totalRecords,
            )
        }
    }
}