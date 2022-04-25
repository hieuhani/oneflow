package vn.periscope.adapters.api.rest

import io.ktor.server.application.*


const val BUSINESS_ID_HEADER = "PSC-BUSINESS-ID"
internal fun ApplicationCall.stringParameter(name: String) = this.parameters[name] ?: throw RuntimeException("stringParameter")
internal fun ApplicationCall.stringHeader(name: String) = this.request.headers[name] ?: throw RuntimeException("Header not found")

internal fun ApplicationCall.longParameter(name: String): Long {
    return try {
        stringParameter(name).toLong()
    } catch (e: NumberFormatException) {
        throw RuntimeException("longParameter")
    }
}

internal fun ApplicationCall.businessIdHeader():Long{
    return try {
        stringHeader(BUSINESS_ID_HEADER).toLong()
    } catch (e: NumberFormatException) {
        throw RuntimeException("BusinessID not found")
    }
}