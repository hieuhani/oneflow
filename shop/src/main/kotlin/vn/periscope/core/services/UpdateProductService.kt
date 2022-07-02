package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.*
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.UpdateProductEntryPort

class UpdateProductService(
    private val transactionService: TransactionService,
    private val updateProductEntryPort: UpdateProductEntryPort,
) : UpdateProductUseCase {

    override suspend fun update(businessId: Long, id: Long, entry: ProductEntry): Product =
        transactionService.transaction {
            return@transaction updateProductEntryPort.update(businessId, id, entry)
        }
}