package vn.periscope.cms.adapters.api.routes.taxonomyterm

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry

@Serializable
data class TaxonomyTermRequest(
    val name: String,
    val machineName: String,
    val description: String,
    val taxonomyId: Long,
    val parentId: Long? = null,
) {
    fun toDomainModel() = TaxonomyTermEntry(
        id = null,
        name,
        machineName,
        description,
        taxonomyId,
        parentId,
    )
}