package vn.periscope.adapters

import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.adapters.api.rest.ProductResource
import vn.periscope.adapters.configs.DatabaseConfiguration
import vn.periscope.adapters.persistence.DatabaseConnector
import vn.periscope.adapters.persistence.GalleryPersistenceAdapter
import vn.periscope.adapters.persistence.ProductAttributePersistenceAdapter
import vn.periscope.core.services.ExposedTransactionService
import vn.periscope.adapters.persistence.ProductPersistenceAdapter
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

    single<TransactionService> {
        ExposedTransactionService(dbConnector = get())
    }


    single { ProductResource(application = get()) }

    single {
        ProductPersistenceAdapter(
            productRepository = get(),
            galleryRepository = get(),
            attributeRepository = get(),
            idProviderRepository = get(),
            productCategoryRepository = get()
        )
    } binds arrayOf(
        GetProductEntryPort::class,
        CreateProductEntryPort::class,
        UpdateProductEntryPort::class,
        DeleteProductEntryPort::class,
        FilterProductEntryPort::class,
        SearchProductEntryPort::class,
    )

    single {
        GalleryPersistenceAdapter(
            galleryRepository = get(),
            idProviderRepository = get()
        )
    } binds arrayOf(
        GetGalleryEntryPort::class
    )

    single {
        ProductAttributePersistenceAdapter(
            productAttributeRepository = get(),
            idProviderRepository = get()
        )
    } binds arrayOf(
        GetProductAttributeEntryPoint::class
    )
}