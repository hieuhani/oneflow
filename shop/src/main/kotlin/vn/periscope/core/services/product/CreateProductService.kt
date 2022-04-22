package vn.periscope.core.services.product

import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.CreateProductUseCase
import vn.periscope.ports.product.models.ProductEntry
import vn.periscope.ports.product.out.CreateProductEntryPort

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort,
) : CreateProductUseCase {

    override suspend fun create(entry: ProductEntry) = transactionService.transaction {
        createProductEntryPort.create(entry)
    }
}