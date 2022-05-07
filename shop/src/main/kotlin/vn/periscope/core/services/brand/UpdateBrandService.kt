package vn.periscope.core.services.brand

import vn.periscope.core.domain.Brand
import vn.periscope.ports.TransactionService
import vn.periscope.ports.UpdateBrandUseCase
import vn.periscope.ports.models.BrandEntry
import vn.periscope.ports.out.UpdateBrandEntryPort

class UpdateBrandService (
    private val transactionService: TransactionService,
    private val updateBrandEntryPort: UpdateBrandEntryPort,
) : UpdateBrandUseCase {
    override suspend fun update(businessId: Long, id: Long, entry: BrandEntry): Brand = transactionService.transaction{
        return@transaction updateBrandEntryPort.update(businessId, id, entry)
    }
}