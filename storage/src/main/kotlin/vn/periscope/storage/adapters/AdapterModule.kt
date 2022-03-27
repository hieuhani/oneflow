package vn.periscope.storage.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.dsl.module
import vn.periscope.storage.adapters.api.routes.healthcheck.HealthCheckRoute
import vn.periscope.storage.adapters.configs.DatabaseConfig
import vn.periscope.storage.adapters.persistence.DatabaseConnector
import vn.periscope.storage.adapters.persistence.ExposedTransactionService
import vn.periscope.storage.ports.TransactionService
import javax.sql.DataSource

val adapterModule = module(createdAtStart = true) {
    single {
        AppBootstrap(application = get())
    }
    single {
        DatabaseConfig(environment = get<Application>().environment)
    }
    single<DataSource> {
        val hikari = get<DatabaseConfig>().hikari
        HikariDataSource(hikari)
    }

    single {
        DatabaseConnector(dataSource = get())
    }

    single<TransactionService> {
        ExposedTransactionService(dbConnector = get())
    }

    single {
        HealthCheckRoute(application = get())
    }
}