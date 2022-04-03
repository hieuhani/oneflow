package vn.periscope.id.adapters.api.routes.user

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.id.adapters.api.routes.user.dto.UserResponseDto
import vn.periscope.id.extentions.inject
import vn.periscope.id.ports.auth.models.UserPrincipal
import vn.periscope.id.ports.user.GetUserUseCase

class UserRoute(application: Application) {
    private val getUserUseCase by inject<GetUserUseCase>()

    init {
        application.routing {
            authenticate {
                get("/me") {
                    val principal = call.principal<UserPrincipal>()
                    val user = getUserUseCase.getUserById(principal!!.userId)
                    call.respond(UserResponseDto.fromDomainModel(user))
                }
            }
        }
    }
}