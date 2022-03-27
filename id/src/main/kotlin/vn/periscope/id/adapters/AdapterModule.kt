package vn.periscope.id.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.id.adapters.api.routes.auth.SignInRoute
import vn.periscope.id.adapters.api.routes.auth.SignUpRoute
import vn.periscope.id.adapters.api.routes.healthcheck.HealthCheckRoute
import vn.periscope.id.adapters.configs.DatabaseConfig
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.adapters.persistence.DatabaseConnector
import vn.periscope.id.adapters.persistence.ExposedTransactionService
import vn.periscope.id.adapters.persistence.user.UserPersistenceAdapter
import vn.periscope.id.adapters.persistence.user.UserRepository
import vn.periscope.id.ports.TransactionService
import vn.periscope.id.ports.auth.JWTService
import vn.periscope.id.ports.user.CreateUserEntryPort
import vn.periscope.id.ports.user.GetUserEntryPort
import javax.sql.DataSource

val adapterModule = module(createdAtStart = true) {
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
        UserRepository()
    }

    single {
        UserPersistenceAdapter(userRepository = get())
    } binds arrayOf(
        CreateUserEntryPort::class,
        GetUserEntryPort::class,
    )

    single { HealthCheckRoute(application = get()) }

    single { SignInRoute(application = get()) }

    single { SignUpRoute(application = get()) }
}