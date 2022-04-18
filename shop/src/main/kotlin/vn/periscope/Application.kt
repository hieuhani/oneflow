package vn.periscope

import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.logger.SLF4JLogger
import vn.periscope.adapters.adapterModule
import vn.periscope.core.coreModule
import vn.periscope.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(KoinPlugin) {
        configureRouting()
        configureSecurity()
        configureMonitoring()
        configureSerialization()
        SLF4JLogger()
        modules(
            module {
                single { this@module }
            },
            adapterModule,
            coreModule,
        )
    }
}
