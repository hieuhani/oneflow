package vn.periscope.core.services

import vn.periscope.ports.GetProductUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.GetAttributeEntryPort
import vn.periscope.ports.out.GetProductCategoryEntryPort
import vn.periscope.ports.out.GetProductEntryPort
import vn.periscope.share.statics.AttributeReferType

class GetProductService(
    private val transactionService: TransactionService,
    private val getProductEntryPort: GetProductEntryPort,
    private val getAttributeEntryPort: GetAttributeEntryPort,
    private val getProductCategoryEntryPort: GetProductCategoryEntryPort
) : GetProductUseCase {

    override suspend fun getById(businessId: Long, id: Long): ProductEntry = transactionService.transaction {
        val product = getProductEntryPort.getById(businessId, id)
        val attribute = getAttributeEntryPort.getByReferTypeAndReferId(businessId, AttributeReferType.PRODUCT, product.id)
        return@transaction getProductEntryPort.findById(id, businessId)!!
    }
}