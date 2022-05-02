package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.TransactionService
import vn.periscope.ports.UpdateProductUseCase
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.UpdateProductEntryPort

class UpdateProductService(
    private val transactionService: TransactionService,
    private val updateProductEntryPort: UpdateProductEntryPort,
) : UpdateProductUseCase {

    override suspend fun update(productEntry: ProductEntry, product: Product) = transactionService.transaction {
        TODO("Not yet implemented")
    }
}