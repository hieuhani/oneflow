package vn.periscope.cms.adapters.api.routes.taxonomy

import io.ktor.http.*
import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.taxonomy.models.TaxonomyFilterEntry

@Serializable
data class TaxonomyFilterRequest(
    val limit: Int?,
    val offset: Int?,
    val machineName: String?,
) {
    companion object {
        fun fromParameters(parameters: Parameters) = TaxonomyFilterRequest(
            limit = parameters["limit"]?.toIntOrNull(),
            offset = parameters["offset"]?.toIntOrNull(),
            machineName = parameters["machineName"],
        )
    }

    fun toDomainModel() = TaxonomyFilterEntry(
        machineName = machineName,
        limit = limit ?: 80,
        offset = offset ?: 0,
    )
}