package vn.periscope.id

import io.ktor.server.application.*
import org.koin.logger.SLF4JLogger
import vn.periscope.id.adapters.adapterModule
import vn.periscope.id.core.coreModule
import vn.periscope.id.plugins.*
import org.koin.dsl.module

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(Koin) {
        SLF4JLogger()
        modules = arrayListOf(
            module {
                single { this@module }
            },
            adapterModule,
            coreModule,
        )
    }
}
