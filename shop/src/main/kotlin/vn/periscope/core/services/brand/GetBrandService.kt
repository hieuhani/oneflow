package vn.periscope.core.services.brand

import vn.periscope.core.domain.Brand
import vn.periscope.ports.GetBrandUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.GetBrandEntryPort

class GetBrandService(
    private val transactionService: TransactionService,
    private val getBrandEntryPort: GetBrandEntryPort
) : GetBrandUseCase {
    override suspend fun getById(businessId: Long, id: Long): Brand  = transactionService.transaction{
        return@transaction getBrandEntryPort.getById(businessId, id)
    }
}