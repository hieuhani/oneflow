package vn.periscope.cms.adapters.api.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import vn.periscope.cms.adapters.api.routes.dto.ContentTypeRequestDto
import vn.periscope.cms.adapters.api.routes.dto.ContentTypeResponseDto
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
            get("/${RESOURCE}") {
                val resources = crudResourceUseCase.getAllResources()
                call.respond(resources.map { ContentTypeResponseDto.fromDomainModel(it) })
            }

            authenticate {
                post("/${RESOURCE}") {
                    val request: ContentTypeRequestDto = call.receive()
                    val contentType = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(ContentTypeResponseDto.fromDomainModel(contentType))
                }

                put("/${RESOURCE}/{id}") {
                    val id = call.longParameter("id")
                    val request: ContentTypeRequestDto = call.receive()
                    val contentType = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(ContentTypeResponseDto.fromDomainModel(contentType))
                }

                delete("/${RESOURCE}/id") {
                    val id = call.longParameter("id")
                    val status = crudResourceUseCase.delete(id)
                    call.respond(status)
                }
            }
        }
    }
}