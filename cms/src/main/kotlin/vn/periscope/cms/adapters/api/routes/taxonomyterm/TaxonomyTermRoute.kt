package vn.periscope.cms.adapters.api.routes.taxonomyterm

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import vn.periscope.cms.adapters.api.routes.dto.PagingResponse
import vn.periscope.cms.extensions.inject
import vn.periscope.cms.extensions.longParameter
import vn.periscope.cms.ports.resource.CrudResourceUseCase
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry

class TaxonomyTermRoute(application: Application) {
    private val crudResourceUseCase by inject<CrudResourceUseCase<TaxonomyTermEntry, Long>>(named("TaxonomyTermResourceService"))

    companion object {
        const val RESOURCE = "taxonomy_terms"
    }

    init {
        application.routing {
            get("/$RESOURCE") {
                val request = TaxonomyTermFilterRequest.fromParameters(call.request.queryParameters)

                val pagedResources = crudResourceUseCase.filter(request.toDomainModel())
                call.respond(
                    PagingResponse(
                        records = pagedResources.records.map {
                            TaxonomyTermResponse.fromDomainModel(
                                it
                            )
                        }),
                )
            }

            authenticate {
                post("/$RESOURCE") {
                    val request: TaxonomyTermRequest = call.receive()
                    val resource = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(TaxonomyTermResponse.fromDomainModel(resource))
                }

                put("/$RESOURCE/{id}") {
                    val id = call.longParameter("id")
                    val request: TaxonomyTermRequest = call.receive()
                    val resource = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(TaxonomyTermResponse.fromDomainModel(resource))
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