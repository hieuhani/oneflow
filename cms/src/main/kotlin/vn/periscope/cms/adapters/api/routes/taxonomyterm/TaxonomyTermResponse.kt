package vn.periscope.cms.adapters.api.routes.taxonomyterm

import kotlinx.serialization.Serializable
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
    }
}