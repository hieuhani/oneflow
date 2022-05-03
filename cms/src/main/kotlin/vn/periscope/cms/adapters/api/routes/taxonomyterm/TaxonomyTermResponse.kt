package vn.periscope.cms.adapters.api.routes.taxonomyterm

import kotlinx.serialization.Serializable
import vn.periscope.cms.adapters.api.routes.dto.PagingResponse
import vn.periscope.cms.ports.resource.models.Paging
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry

@Serializable
data class TaxonomyTermResponse(
    val id: Long,
    val name: String,
    val machineName: String,
    val description: String,
    val taxonomyId: Long,
    val parentId: Long? = null,
) {
    companion object {
        fun fromDomainModel(model: TaxonomyTermEntry) = with(model) {
            TaxonomyTermResponse(
                id = id!!,
                name,
                machineName,
                description,
                taxonomyId,
                parentId
            )
        }

        fun fromPagingDomainModel(pagedResource: Paging<TaxonomyTermEntry>) = with(pagedResource) {
            PagingResponse(
                records = records.map { fromDomainModel(it) },
                limit,
                offset,
                totalRecords,
            )
        }
    }
}