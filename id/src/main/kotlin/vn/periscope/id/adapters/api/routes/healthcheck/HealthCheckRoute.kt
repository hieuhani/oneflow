package vn.periscope.id.adapters.api.routes.healthcheck

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class HealthCheckRoute(application: Application) {
    init {
        application.routing {
            get("/health") {
                call.respond(mapOf("hello" to "world"))
            }
        }
    }
}