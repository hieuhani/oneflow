package vn.periscope.core.services.industry

import vn.periscope.core.domain.Industry
import vn.periscope.ports.GetIndustryUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.GetIndustryEntryPort

class GetIndustryService(
    private val transactionService: TransactionService,
    private val getIndustryEntryPort: GetIndustryEntryPort
) : GetIndustryUseCase {
    override suspend fun findById(businessId: Long, id: Long): Industry = transactionService.transaction {
        return@transaction getIndustryEntryPort.findById(businessId, id)
    }
}