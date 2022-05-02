package vn.periscope.adapters.api.rest

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import io.ktor.utils.io.*
import kotlinx.coroutines.GlobalScope


const val BUSINESS_ID_HEADER = "Psc-Business-Id"
internal fun ApplicationCall.getParameter(name: String) = this.parameters[name] ?: throw RuntimeException("Parameter not found")
internal fun ApplicationCall.getRequestParameter(name: String) = this.request.queryParameters[name] ?: throw RuntimeException("Request parameter not found")
internal fun ApplicationCall.getHeader(name: String) = this.request.headers[name] ?: throw RuntimeException("Header not found")
internal fun ApplicationCall.longParameter(name: String): Long {
    return try {
        getParameter(name).toLong()
    } catch (e: NumberFormatException) {
        throw RuntimeException("longParameter")
    }
}

internal fun ApplicationCall.stringParameter(name: String): String {
    return try {
        getParameter(name)
    } catch (e: NumberFormatException) {
        throw RuntimeException("longParameter")
    }
}

internal fun ApplicationCall.stringRequestParameter(name: String): String {
    return try {
        getRequestParameter(name)
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

internal fun ApplicationCall.abc(){
    val phase = PipelinePhase("phase")
    this.request.pipeline.insertPhaseBefore(ApplicationSendPipeline.Engine, phase)
    this.request.pipeline.intercept(phase) { request ->
        val content: ByteReadChannel = when (request) {
            is OutgoingContent.ByteArrayContent -> ByteReadChannel(request.bytes())
            is OutgoingContent.NoContent -> ByteReadChannel.Empty
            is OutgoingContent.ReadChannelContent -> request.readFrom()
            is OutgoingContent.WriteChannelContent -> GlobalScope.writer(coroutineContext, autoFlush = true) {
                request.writeTo(channel)
            }.channel
            else -> error("")
        }

       content.readUTF8Line()
    }
}