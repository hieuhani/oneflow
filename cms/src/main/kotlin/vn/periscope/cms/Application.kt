package vn.periscope.cms

import io.ktor.server.application.*
import org.koin.logger.SLF4JLogger
import org.koin.dsl.module
import vn.periscope.cms.adapters.adapterModule
import vn.periscope.cms.core.coreModule
import vn.periscope.cms.plugins.KoinPlugin

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
