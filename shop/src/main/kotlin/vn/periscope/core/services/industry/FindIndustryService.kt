package vn.periscope.core.services.industry

import vn.periscope.core.domain.Industry
import vn.periscope.ports.FindIndustryUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.FindIndustryEntryPort

class FindIndustryService(
    private val transactionService: TransactionService,
    private val findIndustryEntryPort: FindIndustryEntryPort
):FindIndustryUseCase {
    override suspend fun find(businessId: Long): List<Industry> = transactionService.transaction {
        return@transaction findIndustryEntryPort.find(businessId)
    }
}