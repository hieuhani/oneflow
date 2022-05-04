package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Industry
import vn.periscope.ports.CreateIndustryUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.IndustryEntry
import vn.periscope.ports.out.CreateIndustryEntryPoint

class CreateIndustryService(
    private val transactionService: TransactionService,
    private val createIndustryEntryPoint: CreateIndustryEntryPoint
) : CreateIndustryUseCase {
    override suspend fun create(businessId: Long, entry: IndustryEntry): Industry =
        transactionService.transaction {
            val industry = Industry(
                0,
                entry.name,
                entry.machineName,
                Clock.System.now(),
                Clock.System.now(),
            )
            return@transaction createIndustryEntryPoint.create(businessId, industry)
        }
}