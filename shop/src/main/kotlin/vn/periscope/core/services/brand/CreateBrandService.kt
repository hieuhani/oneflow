package vn.periscope.core.services.brand

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Brand
import vn.periscope.ports.CreateBrandUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.BrandEntry
import vn.periscope.ports.out.CreateBrandEntryPort

class CreateBrandService(
    private val transactionService: TransactionService,
    private val createBrandEntryPort: CreateBrandEntryPort
) : CreateBrandUseCase {
    override suspend fun create(businessId: Long, entry: BrandEntry): Brand = transactionService.transaction {
        return@transaction createBrandEntryPort.create(businessId, entry)
    }
}