package vn.periscope.cms.adapters.api.routes.contenttype

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import vn.periscope.cms.extensions.inject
import vn.periscope.cms.extensions.longParameter
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry
import vn.periscope.cms.ports.resource.CrudResourceUseCase

class ContentTypeRoute(application: Application) {
    private val crudResourceUseCase by inject<CrudResourceUseCase<ContentTypeEntry, Long>>(named("ContentTypeResourceService"))

    companion object {
        const val RESOURCE = "content_types"
    }

    init {
        application.routing {
            get("/$RESOURCE") {
                val request = ContentTypeFilterRequest.fromParameters(call.request.queryParameters)
                val pagedResource = crudResourceUseCase.filter(request.toDomainModel())
                call.respond(ContentTypeResponse.fromPagingDomainModel(pagedResource))
            }

            authenticate {
                post("/$RESOURCE") {
                    val request: ContentTypeRequest = call.receive()
                    val contentType = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(ContentTypeResponse.fromDomainModel(contentType))
                }

                put("/$RESOURCE/{id}") {
                    val id = call.longParameter("id")
                    val request: ContentTypeRequest = call.receive()
                    val contentType = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(ContentTypeResponse.fromDomainModel(contentType))
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