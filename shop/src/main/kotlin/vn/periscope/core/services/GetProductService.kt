package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.GetProductUseCase
import vn.periscope.ports.product.models.ProductEntry
import vn.periscope.ports.product.out.GetProductEntryPort

class GetProductService(
    private val transactionService: TransactionService,
    private val getProductEntryPort: GetProductEntryPort,
) : GetProductUseCase {
    override suspend fun findById(id: Long): ProductEntry {
        TODO("Not yet implemented")
    }
}