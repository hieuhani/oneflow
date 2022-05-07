package vn.periscope.core.services.industry

import vn.periscope.ports.DeleteIndustryUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.DeleteIndustryEntryPort

class DeleteIndustryService(
    private val transactionService: TransactionService,
    private val deleteIndustryEntryPort: DeleteIndustryEntryPort,
): DeleteIndustryUseCase {
    override suspend fun delete(businessId: Long, id: Long) = transactionService.transaction {
        deleteIndustryEntryPort.delete(businessId, id)
    }
}