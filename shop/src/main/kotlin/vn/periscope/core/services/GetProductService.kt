package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.GetProductUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.GetProductEntryPort

class GetProductService(
    private val transactionService: TransactionService,
    private val getProductEntryPort: GetProductEntryPort,
) : GetProductUseCase {

    override suspend fun findById(businessId: Long, id: Long): Product = transactionService.transaction {
        return@transaction getProductEntryPort.findById(id, businessId)!!
    }
}