package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.CreateProductUseCase
import vn.periscope.ports.product.models.ProductEntry
import vn.periscope.ports.product.out.CreateGalleryEntryPort
import vn.periscope.ports.product.out.CreateProductAttributeEntryPoint
import vn.periscope.ports.product.out.CreateProductEntryPort

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort
) : CreateProductUseCase {

    override suspend fun create(entry: ProductEntry): Product {
        val product = Product.create(entry);
        storage(product)
        return product
    }

    private suspend fun storage(product: Product) = transactionService.transaction {
        createProductEntryPort.create(product)
    }
}