package vn.periscope

import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.logger.SLF4JLogger
import vn.periscope.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSecurity()
    configureMonitoring()
    configureSerialization()
}
