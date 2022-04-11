package vn.periscope.adapters

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.adapters.api.routers.ProductRoute
import vn.periscope.core.product.GetProductService

val adapterModule = module(createdAtStart = true) {

    single { ProductRoute(application = get()) }

    single {
        GetProductService(
        )
    } binds arrayOf(
        GetProductService::class,
    )
}