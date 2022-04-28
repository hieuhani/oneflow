package vn.periscope.core

import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.core.services.*
import vn.periscope.ports.*

val coreModule = module(createdAtStart = true) {
    single {
        CreateProductService(
            transactionService = get(),
            createProductEntryPort = get(),
            getProductEntryPort = get(),
            getGalleryUseCase = get(),
            getProductAttributeUseCase = get()
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
        FilterAndSearchProductService(
            transactionService = get(),
            filterAndSearchProductEntryPort = get(),
        )
    } binds arrayOf(
        FilterAndSearchProductUseCase::class,
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