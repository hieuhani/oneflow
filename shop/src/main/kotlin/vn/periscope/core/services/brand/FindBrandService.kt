package vn.periscope.core.services.brand

import vn.periscope.core.domain.Brand
import vn.periscope.ports.FindBrandUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.FindBrandEntryPort

class FindBrandService(
    private val transactionService: TransactionService,
    private val findBrandEntryPort: FindBrandEntryPort,
):FindBrandUseCase {
    override suspend fun find(businessId: Long): List<Brand> = transactionService.transaction {
        return@transaction findBrandEntryPort.find(businessId)
    }
}