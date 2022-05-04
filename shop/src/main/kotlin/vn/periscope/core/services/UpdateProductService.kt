package vn.periscope.core.services

import vn.periscope.core.domain.Product
import vn.periscope.ports.TransactionService
import vn.periscope.ports.UpdateProductUseCase
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.UpdateProductEntryPort

class UpdateProductService(
    private val transactionService: TransactionService,
    private val updateProductEntryPort: UpdateProductEntryPort,
    private val createAttributeUseCase: CreateAttributeService
) : UpdateProductUseCase {

    override suspend fun update(entry: ProductEntry, product: Product) = transactionService.transaction {
        product.name = entry.name
        product.brandId = entry.brandId
        product.industryId = entry.industryId
        product.categoryIds = entry.categoryIds

    }
}