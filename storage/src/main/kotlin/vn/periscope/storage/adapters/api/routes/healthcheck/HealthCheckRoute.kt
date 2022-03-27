package vn.periscope.storage.adapters.api.routes.healthcheck

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class HealthCheckRoute(application: Application) {
    init {
        application.routing {
            get("/health/live") {
                call.respond(mapOf("hello" to "world"))
            }
            get("/health/ready") {
                call.respond(mapOf("hello" to "world"))
            }
        }
    }
}