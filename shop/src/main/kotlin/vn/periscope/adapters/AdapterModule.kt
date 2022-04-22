package vn.periscope.adapters

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.adapters.api.routers.ProductRoute
import vn.periscope.adapters.persistence.product.ProductPersistenceAdapter
import vn.periscope.core.services.product.GetProductService
import vn.periscope.ports.product.out.*

val adapterModule = module(createdAtStart = true) {

    single { ProductRoute(application = get()) }

    single {
        ProductPersistenceAdapter(productRepository = get())
    } binds arrayOf(
        GetProductEntryPort::class,
        CreateProductEntryPort::class,
        UpdateProductEntryPort::class,
        DeleteProductEntryPort::class,
        FilterProductEntryPort::class,
        SearchProductEntryPort::class,
    )
}