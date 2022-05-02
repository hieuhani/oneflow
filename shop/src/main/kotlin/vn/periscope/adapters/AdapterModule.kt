package vn.periscope.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.adapters.api.rest.ProductResource
import vn.periscope.adapters.configs.DatabaseConfiguration
import vn.periscope.adapters.persistence.*
import vn.periscope.adapters.persistence.entity.*
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.services.ExposedTransactionService
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.*
import javax.sql.DataSource

val adapterModule = module(createdAtStart = true) {

    single<DataSource> {
        val hikari = get<DatabaseConfiguration>().hikari
        HikariDataSource(hikari)
    }

    single {
        DatabaseConnector(dataSource = get())
    }

    single {
        DatabaseConfiguration(environment = get<Application>().environment)
    }

    single<TransactionService> {
        ExposedTransactionService(dbConnector = get())
    }

    single {
        ProductTable
    }

    single {
        AttributeTable
    }

    single {
        AttributeValueTable
    }

    single {
        GalleryTable
    }

    single {
        ProductCategoryTable
    }

    single {
        IdProviderRepository
    }

    single {
        ProductRepository(table = get())
    }

    single {
        AttributeRepository(table = get())
    }

    single {
        AttributeValueRepository(table = get())
    }

    single {
        GalleryRepository(table = get())
    }

    single {
        ProductCategoryRepository(table = get())
    }

    single { ProductResource(application = get()) }

    single {
        CreateProductPersistence(
            productRepository = get(),
            galleryRepository = get(),
            attributeRepository = get(),
            productCategoryRepository = get(),
            attributeValueRepository = get()
        )
    } binds arrayOf(
        CreateProductEntryPort::class,
    )

    single {
        GetProductPersistence(
            productRepository = get(),
            galleryRepository = get(),
            attributeRepository = get(),
            idProviderRepository = get(),
            productCategoryRepository = get(),
            attributeValueRepository = get()
        )
    } bind (
            GetProductEntryPort::class
            )

    single {
        DeleteProductPersistence(
            productRepository = get(),
        )
    } bind (
            DeleteProductEntryPort::class
            )

    single {
        GetGalleryPersistence(
            galleryRepository = get(),
            idProviderRepository = get()
        )
    } bind (
            GetGalleryEntryPort::class
            )

    single {
        GetAttributePersistence(
            productAttributeRepository = get(),
            idProviderRepository = get()
        )
    } bind (
            GetAttributeEntryPoint::class
            )

    single {
        FilterAndSearchPersistence(
            productRepository = get(),
            galleryRepository = get(),
            attributeRepository = get(),
            productCategoryRepository = get(),
            attributeValueRepository = get()
        )
    } bind (
            FilterAndSearchProductEntryPort::class
            )

    single {
        UpdateProductPersistence()
    } bind (
            UpdateProductEntryPort::class
            )
}