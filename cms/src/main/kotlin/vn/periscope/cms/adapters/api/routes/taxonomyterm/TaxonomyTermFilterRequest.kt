package vn.periscope.cms.adapters.api.routes.taxonomyterm

import io.ktor.http.*
import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermFilterEntry

@Serializable
data class TaxonomyTermFilterRequest(
    val taxonomyId: Long?,
    val parentId: Long?,
    val machineName: String?,
    val limit: Int = 80,
    val offset: Int = 0,
) {
    companion object {
        fun fromParameters(parameters: Parameters): TaxonomyTermFilterRequest {
            return TaxonomyTermFilterRequest(
                taxonomyId = parameters["taxonomyId"]?.toLongOrNull(),
                parentId = parameters["parentId"]?.toLongOrNull(),
                machineName = parameters["machineName"],
            )
        }
    }
    fun toDomainModel() = TaxonomyTermFilterEntry(
        taxonomyId,
        parentId,
        machineName,
        limit,
        offset,
    )
}