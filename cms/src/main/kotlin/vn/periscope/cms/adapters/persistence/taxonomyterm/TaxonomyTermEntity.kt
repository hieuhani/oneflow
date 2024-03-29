package vn.periscope.cms.adapters.persistence.taxonomyterm

import vn.periscope.cms.adapters.persistence.resource.ResourceEntity
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry

data class TaxonomyTermEntity(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
    val taxonomyId: Long,
    val parentId: Long? = null,
) : ResourceEntity<TaxonomyTermEntry> {
    override fun toEntry() = TaxonomyTermEntry(
        id,
        name,
        machineName,
        description,
        taxonomyId,
        parentId,
    )
}