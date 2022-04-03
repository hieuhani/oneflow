package vn.periscope.storage.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.minio.MinioClient
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.id.adapters.Auth0JWTService
import vn.periscope.id.adapters.configs.JWTConfig
import vn.periscope.id.ports.auth.JWTService
import vn.periscope.storage.adapters.api.routes.healthcheck.HealthCheckRoute
import vn.periscope.storage.adapters.api.routes.storage.UploadFileRoute
import vn.periscope.storage.adapters.configs.DatabaseConfig
import vn.periscope.storage.adapters.configs.StorageConfig
import vn.periscope.storage.adapters.persistence.DatabaseConnector
import vn.periscope.storage.adapters.persistence.ExposedTransactionService
import vn.periscope.storage.adapters.persistence.file.FilePersistenceAdapter
import vn.periscope.storage.adapters.persistence.file.FileRepository
import vn.periscope.storage.core.storage.MinioConfigProperties
import vn.periscope.storage.ports.TransactionService
import vn.periscope.storage.ports.file.CreateFileEntryPort
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

    single {
        StorageConfig(environment = get<Application>().environment)
    }

    single {
        get<StorageConfig>().minio
    }

    single<MinioClient> {
        val minioConfig = get<MinioConfigProperties>()
        MinioClient.builder()
            .endpoint(minioConfig.host)
            .credentials(
                minioConfig.accessKey,
                minioConfig.secretKey,
            )
            .build()
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
        FileRepository()
    }

    single {
        FilePersistenceAdapter(fileRepository = get())
    } binds arrayOf(
        CreateFileEntryPort::class,
    )

    single {
        HealthCheckRoute(application = get())
    }

    single {
        UploadFileRoute(application = get())
    }
}