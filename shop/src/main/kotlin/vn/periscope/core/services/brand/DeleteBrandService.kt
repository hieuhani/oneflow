package vn.periscope.core.services.brand

import vn.periscope.ports.DeleteBrandUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.DeleteBrandEntryPort

class DeleteBrandService(
    private val transactionService: TransactionService,
    private val deleteBrandEntryPort: DeleteBrandEntryPort,
):DeleteBrandUseCase {
    override suspend fun delete(id: Long, businessId: Long) = transactionService.transaction {
        deleteBrandEntryPort.delete(businessId, id)
    }
}