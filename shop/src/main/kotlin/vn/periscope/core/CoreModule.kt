package vn.periscope.core

import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.core.services.*
import vn.periscope.ports.*

val coreModule = module(createdAtStart = true) {
    single {
        GetProductService(
            transactionService = get(),
            getProductEntryPort = get()
        )
    } bind (
            GetProductUseCase::class
            )

    single {
        GetAttributeService(
            getAttributeEntryPoint = get()
        )
    } bind (
            GetAttributeUseCase::class
            )

    single {
        CreateAttributeService(
            getAttributeEntryPoint = get()
        )
    } bind (
            CreateAttributeUseCase::class
            )

    single {
        CreateProductService(
            transactionService = get(),
            createProductEntryPort = get(),
            getProductEntryPort = get(),
            createAttributeUseCase = get()
        )
    } binds arrayOf(
        CreateProductUseCase::class,
    )

    single {
        UpdateProductService(
            transactionService = get(),
            updateProductEntryPort = get(),
            createAttributeUseCase = get()
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
}