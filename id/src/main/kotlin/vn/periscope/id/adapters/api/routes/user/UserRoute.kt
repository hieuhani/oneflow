package vn.periscope.id.adapters.api.routes.user

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import vn.periscope.id.ports.auth.models.UserPrincipal

class UserRoute(application: Application) {
    init {
        application.routing {
            authenticate {
                get("/me") {
                    val principal = call.principal<UserPrincipal>()


                    call.respondText("Hehe ${principal!!.userId}")
                }
            }
        }
    }
}