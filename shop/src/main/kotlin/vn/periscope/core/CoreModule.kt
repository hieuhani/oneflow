package vn.periscope.core

import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import vn.periscope.core.services.*
import vn.periscope.core.services.brand.*
import vn.periscope.core.services.industry.CreateIndustryService
import vn.periscope.core.services.industry.DeleteIndustryService
import vn.periscope.core.services.industry.FindIndustryService
import vn.periscope.core.services.industry.GetIndustryService
import vn.periscope.ports.*
import vn.periscope.ports.out.DeleteBrandEntryPort
import vn.periscope.ports.out.DeleteIndustryEntryPort
import vn.periscope.ports.out.FindBrandEntryPort
import vn.periscope.ports.out.FindIndustryEntryPort

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
        FindProductService(
            transactionService = get(),
            findProductEntryPort = get(),
        )
    } binds arrayOf(
        FindProductUseCase::class,
    )

    /**
     * Brand
     */

    single {
        CreateBrandService(
            transactionService = get(),
            createBrandEntryPort = get()
        )
    } bind (
            CreateBrandUseCase::class
            )

    single {
        GetBrandService(
            transactionService = get(),
            getBrandEntryPort = get()
        )
    } bind (
            GetBrandUseCase::class
            )


    single {
        DeleteBrandService(
            transactionService = get(),
            deleteBrandEntryPort = get()
        )
    } bind (
            DeleteBrandUseCase::class
            )

    single {
        UpdateBrandService(
            transactionService = get(),
            updateBrandEntryPort = get(),
        )
    } bind (
            UpdateBrandUseCase::class
            )

    single {
        FindBrandService(
            transactionService = get(),
            findBrandEntryPort = get()
        )
    } bind (
            FindBrandUseCase::class
            )

    /**
     * Industry
     */

    single {
        CreateIndustryService(
            transactionService = get(),
            createIndustryEntryPort = get()
        )
    } bind (
            CreateIndustryUseCase::class
            )

    single {
        GetIndustryService(
            transactionService = get(),
            getIndustryEntryPort = get()
        )
    } bind (
            GetIndustryUseCase::class
            )


    single {
        DeleteIndustryService(
            transactionService = get(),
            deleteIndustryEntryPort = get()
        )
    } bind (
            DeleteIndustryUseCase::class
            )

    single {
        UpdateIndustryService(
            transactionService = get(),
            updateIndustryEntryPort = get(),
        )
    } bind (
            UpdateIndustryUseCase::class
            )

    single {
        FindIndustryService(
            transactionService = get(),
            findIndustryEntryPort = get()
        )
    } bind (
            FindIndustryUseCase::class
            )
}