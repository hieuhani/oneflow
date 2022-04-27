package vn.periscope.adapters.api.rest

import io.ktor.server.application.*


const val BUSINESS_ID_HEADER = "Psc-Business-Id"
internal fun ApplicationCall.getParameter(name: String) = this.parameters[name] ?: throw RuntimeException("stringParameter")
internal fun ApplicationCall.getHeader(name: String) = this.request.headers[name] ?: throw RuntimeException("Header not found")
internal fun ApplicationCall.longParameter(name: String): Long {
    return try {
        getParameter(name).toLong()
    } catch (e: NumberFormatException) {
        throw RuntimeException("longParameter")
    }
}

internal fun ApplicationCall.longHeader(name: String):Long{
    return try {
        getHeader(name).toLong()
    } catch (e: NumberFormatException) {
        throw RuntimeException("BusinessID not found")
    }
}