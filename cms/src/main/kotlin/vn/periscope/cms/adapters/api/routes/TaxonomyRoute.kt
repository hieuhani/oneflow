package vn.periscope.cms.adapters.api.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.cms.adapters.api.routes.dto.CreateTaxonomyRequestDto
import vn.periscope.cms.adapters.api.routes.dto.TaxonomyResponseDto
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
                val taxonomies = crudResourceUseCase.getAllResources()
                call.respond(taxonomies.map { TaxonomyResponseDto.fromDomainModel(it) })
            }

            authenticate {
                post("/$RESOURCE") {
                    val request: CreateTaxonomyRequestDto = call.receive()
                    val taxonomy = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(TaxonomyResponseDto.fromDomainModel(taxonomy))
                }

                put("/$RESOURCE/{id}") {
                    val id = call.longParameter("id")
                    val request: CreateTaxonomyRequestDto = call.receive()
                    val taxonomy = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(TaxonomyResponseDto.fromDomainModel(taxonomy))
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