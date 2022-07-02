package vn.periscope.core.services

import vn.periscope.ports.CreateProductUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.CreateProductEntryPort

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort,
) : CreateProductUseCase {

    override suspend fun create(businessId: Long, entry: ProductEntry) = transactionService.transaction {
        createProductEntryPort.create(businessId, entry)
    }
}