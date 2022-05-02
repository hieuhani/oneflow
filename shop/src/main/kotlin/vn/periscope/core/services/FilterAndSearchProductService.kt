package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.TransactionService
import vn.periscope.ports.FilterAndSearchProductUseCase
import vn.periscope.ports.out.FilterAndSearchProductEntryPort

class FilterAndSearchProductService(
    private val transactionService: TransactionService,
    private val filterAndSearchProductEntryPort: FilterAndSearchProductEntryPort,
) : FilterAndSearchProductUseCase {
    override suspend fun filterAndSearch(businessId: Long): List<Product> = transactionService.transaction {
        return@transaction filterAndSearchProductEntryPort.filter(businessId)
    }
}