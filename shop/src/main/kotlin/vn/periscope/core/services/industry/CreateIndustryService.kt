package vn.periscope.core.services.industry

import vn.periscope.core.domain.Industry
import vn.periscope.ports.CreateIndustryUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.IndustryEntry
import vn.periscope.ports.out.CreateIndustryEntryPort

class CreateIndustryService(
    private val transactionService: TransactionService,
    private val createIndustryEntryPort: CreateIndustryEntryPort
) : CreateIndustryUseCase {
    override suspend fun create(businessId: Long, entry: IndustryEntry): Industry =
        transactionService.transaction {
            return@transaction createIndustryEntryPort.create(businessId, entry)
        }
}