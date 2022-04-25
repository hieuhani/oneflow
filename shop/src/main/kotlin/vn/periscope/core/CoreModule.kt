package vn.periscope.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.core.services.*
import vn.periscope.ports.product.*

val coreModule = module(createdAtStart = true) {
    single {
        CreateProductService(
            transactionService = get(),
            createProductEntryPort = get(),
            createGalleryEntryPort = get(),
            createProductAttributeEntryPoint = get()
        )
    } binds arrayOf(
        CreateProductUseCase::class,
    )

    single {
        GetProductService(
            transactionService = get(),
            getProductEntryPort = get(),
        )
    } binds arrayOf(
        GetProductUseCase::class,
    )

    single {
        UpdateProductService(
            transactionService = get(),
            updateProductEntryPort = get(),
        )
    } binds arrayOf(
        UpdateProductUseCase::class,
    )

    single {
        DeleteProductService(
            transactionService = get(),
            deleteProductEntryPort = get(),
        )
    } binds arrayOf(
        DeleteProductUseCase::class,
    )

    single {
        FilterProductService(
            transactionService = get(),
            filterProductEntryPort = get(),
        )
    } binds arrayOf(
        FilterProductUseCase::class,
    )

    single {
        SearchProductService(
            transactionService = get(),
            searchProductEntryPort = get(),
        )
    } binds arrayOf(
        SearchProductUseCase::class,
    )

}