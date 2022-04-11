package vn.periscope.cms.adapters.api.routes.dto

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

@Serializable
data class TaxonomyResponseDto(
    val id: Long,
    val name: String,
    val machineName: String,
    val description: String,
) {
    companion object {
        fun fromDomainModel(model: TaxonomyEntry) = with(model) {
            TaxonomyResponseDto(
                id = id!!,
                name,
                machineName,
                description,
            )
        }
    }
}