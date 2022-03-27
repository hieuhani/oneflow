package vn.periscope.storage

import io.ktor.server.application.*
import org.koin.logger.SLF4JLogger
import vn.periscope.storage.adapters.adapterModule
import vn.periscope.storage.plugins.KoinPlugin
import org.koin.dsl.module
import vn.periscope.storage.core.coreModule

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(KoinPlugin) {
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
