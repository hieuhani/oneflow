package vn.periscope.id.adapters.configs

import com.zaxxer.hikari.HikariConfig
import io.ktor.server.application.*
import io.ktor.server.config.*
import java.util.*

class DatabaseConfig(
    environment: ApplicationEnvironment
) {
    val hikari by lazy {
        val dbConfig = environment.config.config("database.hikari")
        HikariConfig(dbConfig.toProperties())
    }
}

private fun ApplicationConfig.toProperties() = Properties().also {
    for (e in this.keys()) {
        it.setProperty(e, this.property(e).getString())
    }
}