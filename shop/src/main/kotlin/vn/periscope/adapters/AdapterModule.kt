package vn.periscope.adapters

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.adapters.api.rest.BrandResource
import vn.periscope.adapters.api.rest.IndustryResource
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
        ProductCategoryTable
    }

    single {
        IdProviderRepository
    }

    single {
        BrandTable
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
        ProductCategoryRepository(table = get())
    }

    single { ProductResource(application = get()) }

    single {
        BrandRepository
    }

    single {
        BrandResource(application = get())
    }

    single {
        IndustryRepository
    }

    single {
        IndustryResource(application = get())
    }

    single {
        CreateProductPersistence(
            productRepository = get(),
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
        GetAttributePersistence(
            productAttributeRepository = get(),
            idProviderRepository = get()
        )
    } bind (
            GetAttributeEntryPoint::class
            )

    single {
        FindPersistence(
            productRepository = get(),
            attributeRepository = get(),
            productCategoryRepository = get(),
            attributeValueRepository = get()
        )
    } bind (
            FindProductEntryPort::class
            )

    single {
        UpdateProductPersistence()
    } bind (
            UpdateProductEntryPort::class
            )

    single {
        BrandPersistence(
            brandRepository = get(),
        )
    } binds arrayOf(
        CreateBrandEntryPort::class,
        GetBrandEntryPort::class,
        UpdateBrandEntryPort::class,
        DeleteBrandEntryPort::class,
        FindBrandEntryPort::class
    )

    single {
        IndustryPersistence(
            industryRepository = get(),
        )
    } binds arrayOf(
        CreateIndustryEntryPoint::class,
        GetIndustryEntryPort::class,
        UpdateIndustryEntryPort::class,
        DeleteIndustryEntryPort::class,
        FindIndustryEntryPort::class
    )
}