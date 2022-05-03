package vn.periscope.cms.adapters.api.routes.taxonomy

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.cms.extensions.inject
import vn.periscope.cms.extensions.longParameter
import vn.periscope.cms.ports.resource.CrudResourceUseCase
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

class TaxonomyRoute(application: Application) {
    private val crudResourceUseCase by inject<CrudResourceUseCase<TaxonomyEntry, Long>>()

    companion object {
        const val RESOURCE = "taxonomies"
    }

    init {
        application.routing {
            get("/$RESOURCE") {
                val request = TaxonomyFilterRequest.fromParameters(call.request.queryParameters)
                val pagedResource = crudResourceUseCase.filter(request.toDomainModel())
                call.respond(TaxonomyResponse.fromPagingDomainModel(pagedResource))
            }

            authenticate {
                post("/$RESOURCE") {
                    val request: CreateTaxonomyRequest = call.receive()
                    val taxonomy = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(TaxonomyResponse.fromDomainModel(taxonomy))
                }

                put("/$RESOURCE/{id}") {
                    val id = call.longParameter("id")
                    val request: CreateTaxonomyRequest = call.receive()
                    val taxonomy = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(TaxonomyResponse.fromDomainModel(taxonomy))
                }

                delete("/$RESOURCE/id") {
                    val id = call.longParameter("id")
                    val status = crudResourceUseCase.delete(id)
                    call.respond(status)
                }
            }
        }
    }
}