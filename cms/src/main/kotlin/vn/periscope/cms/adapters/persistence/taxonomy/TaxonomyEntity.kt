package vn.periscope.cms.adapters.persistence.taxonomy

import vn.periscope.cms.adapters.persistence.resource.ResourceEntity
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

data class TaxonomyEntity(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
) : ResourceEntity<TaxonomyEntry> {
    override fun toEntry() = TaxonomyEntry(
        id, name, machineName, description,
    )
}