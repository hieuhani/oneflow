package vn.periscope.cms.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.cms.adapters.api.routes.ContentRoute
import vn.periscope.cms.adapters.configs.DatabaseConfig
import vn.periscope.cms.adapters.persistence.DatabaseConnector
import vn.periscope.cms.adapters.persistence.ExposedTransactionService
import vn.periscope.cms.adapters.persistence.content.ContentPersistenceAdapter
import vn.periscope.cms.adapters.persistence.content.ContentRepository
import vn.periscope.cms.ports.TransactionService
import vn.periscope.cms.ports.content.output.*
import vn.periscope.id.adapters.Auth0JWTService
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.ports.auth.JWTService
import javax.sql.DataSource

val adapterModule = module(createdAtStart = true) {
    single {
        ContentRoute(application = get())
    }
    single {
        AppBootstrap(application = get())
    }

    single {
        DatabaseConfig(environment = get<Application>().environment)
    }

    single {
        JWTConfig(environment = get<Application>().environment)
    }

    single<JWTService> {
        val jwtConfig = get<JWTConfig>()
        Auth0JWTService(jwtConfig)
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
        ContentRepository
    }

    single {
        ContentPersistenceAdapter(contentRepository = get())
    } binds arrayOf(
        GetContentsEntryPort::class,
        GetContentEntryPort::class,
        CreateContentEntryPort::class,
        UpdateContentEntryPort::class,
        DeleteContentEntryPort::class,
    )
}