package vn.periscope.cms.adapters.api.routes.contentfieldvalue

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import vn.periscope.cms.extensions.inject
import vn.periscope.cms.extensions.longParameter
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry
import vn.periscope.cms.ports.resource.CrudResourceUseCase

class ContentFieldValueRoute(application: Application) {
    private val crudResourceUseCase by inject<CrudResourceUseCase<ContentFieldValueEntry, Long>>(named("ContentFieldValueResourceService"))

    companion object {
        const val RESOURCE = "content_field_values"
    }

    init {
        application.routing {
            get("/$RESOURCE") {
                val resources = crudResourceUseCase.getAllResources()
                call.respond(resources.map { ContentFieldValueResponseDto.fromDomainModel(it) })
            }

            authenticate {
                post("/$RESOURCE") {
                    val request: ContentFieldValueRequestDto = call.receive()
                    val resource = crudResourceUseCase.create(request.toDomainModel())
                    call.respond(ContentFieldValueResponseDto.fromDomainModel(resource))
                }

                put("/$RESOURCE/{id}") {
                    val id = call.longParameter("id")
                    val request: ContentFieldValueRequestDto = call.receive()
                    val resource = crudResourceUseCase.update(id, request.toDomainModel())
                    call.respond(ContentFieldValueResponseDto.fromDomainModel(resource))
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