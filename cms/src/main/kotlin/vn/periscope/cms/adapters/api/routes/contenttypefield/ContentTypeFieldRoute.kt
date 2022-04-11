package vn.periscope.cms.adapters.api.routes.contenttypefield

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import vn.periscope.cms.extensions.inject
import vn.periscope.cms.extensions.longParameter
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.resource.CrudResourceUseCase

class ContentTypeFieldRoute(application: Application) {
    private val crudResourceUseCase by inject<CrudResourceUseCase<ContentTypeFieldEntry, Long>>(named("ContentTypeFieldResourceService"))

    companion object {
        const val RESOURCE = "content_type_fields"
    }

    init {
        application.routing {
            get("/$RESOURCE") {
                val resources = crudResourceUseCase.getAllResources()
                call.respond(resources.map { ContentTypeFieldResponseDto.fromDomainModel(it) })
            }

            authenticate {
                post("/$RESOURCE") {
                    val request: ContentTypeFieldRequestDto = call.receive()
                    val resource = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(ContentTypeFieldResponseDto.fromDomainModel(resource))
                }

                put("/$RESOURCE/{id}") {
                    val id = call.longParameter("id")
                    val request: ContentTypeFieldRequestDto = call.receive()
                    val resource = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(ContentTypeFieldResponseDto.fromDomainModel(resource))
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