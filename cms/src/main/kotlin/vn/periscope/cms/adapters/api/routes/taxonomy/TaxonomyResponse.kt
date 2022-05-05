package vn.periscope.cms.adapters.api.routes.taxonomy

import kotlinx.serialization.Serializable
import vn.periscope.cms.adapters.api.routes.dto.PagingResponse
import vn.periscope.cms.ports.resource.models.Paging
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

@Serializable
data class TaxonomyResponse(
    val id: Long,
    val name: String,
    val machineName: String,
    val description: String,
) {
    companion object {
        fun fromDomainModel(model: TaxonomyEntry) = with(model) {
            TaxonomyResponse(
                id = id!!,
                name,
                machineName,
                description,
            )
        }

        fun fromPagingDomainModel(pagedResource: Paging<TaxonomyEntry>) = with(pagedResource) {
            PagingResponse(
                records = records.map { fromDomainModel(it) },
                limit,
                offset,
                totalRecords,
            )
        }
    }
}