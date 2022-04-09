package vn.periscope.cms.adapters.api.routes

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.cms.adapters.api.routes.dto.ContentResponseDto
import vn.periscope.cms.adapters.api.routes.dto.CreateContentRequestDto
import vn.periscope.cms.extensions.inject
import vn.periscope.cms.ports.content.CreateContentUseCase
import vn.periscope.cms.ports.content.GetContentsUseCase
import vn.periscope.id.ports.auth.models.UserPrincipal

class ContentRoute(application: Application) {
    private val getContentsUseCase by inject<GetContentsUseCase>()
    private val createContentUseCase by inject<CreateContentUseCase>()

    init {
        application.routing {
            get("/contents") {
                val contents = getContentsUseCase.getContents()
                call.respond(contents.map { ContentResponseDto.fromDomainModel(it) })
            }
            post("/contents") {
                val principal = call.principal<UserPrincipal>()
                val request : CreateContentRequestDto = call.receive()
                val content = createContentUseCase.createContent(request.toDomainModel(principal!!.userId))
                call.respond(ContentResponseDto.fromDomainModel(content))
            }
        }
    }
}