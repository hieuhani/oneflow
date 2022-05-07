package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.TransactionService
import vn.periscope.ports.FindProductUseCase
import vn.periscope.ports.out.FindProductEntryPort

class FindProductService(
    private val transactionService: TransactionService,
    private val findProductEntryPort: FindProductEntryPort,
) : FindProductUseCase {
    override suspend fun filterAndSearch(businessId: Long): List<Product> = transactionService.transaction {
        return@transaction findProductEntryPort.find(businessId)
    }
}