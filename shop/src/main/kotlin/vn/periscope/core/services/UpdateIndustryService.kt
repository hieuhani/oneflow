package vn.periscope.core.services

import vn.periscope.core.domain.Industry
import vn.periscope.ports.TransactionService
import vn.periscope.ports.UpdateIndustryUseCase
import vn.periscope.ports.models.IndustryEntry
import vn.periscope.ports.out.UpdateIndustryEntryPort

class UpdateIndustryService(
    private val transactionService: TransactionService,
    private val updateIndustryEntryPort: UpdateIndustryEntryPort,
) : UpdateIndustryUseCase {
    override suspend fun update(businessId: Long, id: Long, entry: IndustryEntry): Industry =
        transactionService.transaction {
            return@transaction updateIndustryEntryPort.update(businessId, id, entry)
        }
}