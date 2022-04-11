package vn.periscope.cms.extensions

import io.ktor.server.application.*

internal fun ApplicationCall.stringParameter(name: String) = this.parameters[name] ?: throw RuntimeException("stringParameter")

internal fun ApplicationCall.longParameter(name: String): Long {
    return try {
        stringParameter(name).toLong()
    } catch (e: NumberFormatException) {
        throw RuntimeException("longParameter")
    }
}