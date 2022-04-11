package vn.periscope.cms.adapters.api.routes.dto

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

@Serializable
data class CreateTaxonomyRequestDto(
    val name: String,
    val machineName: String,
    val description: String,
) {
    fun toDomainModel() = TaxonomyEntry(
        id = null,
        name,
        machineName,
        description,
    )
}