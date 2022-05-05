package vn.periscope.core.services

import vn.periscope.core.domain.Brand
import vn.periscope.ports.CreateBrandUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.BrandEntry
import vn.periscope.ports.out.CreateBrandEntryPoint

class CreateBrandService(
    private val transactionService: TransactionService,
    private val createBrandEntryPoint: CreateBrandEntryPoint
) : CreateBrandUseCase {
    override suspend fun create(businessId: Long, entry: BrandEntry): Brand = transactionService.transaction {
        val brand = Brand.create(entry)
        return@transaction createBrandEntryPoint.create(brand)
    }
}