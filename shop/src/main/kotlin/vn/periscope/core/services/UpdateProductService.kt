package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.UpdateProductUseCase
import vn.periscope.ports.product.out.UpdateProductEntryPort

class UpdateProductService(
    private val transactionService: TransactionService,
    private val updateProductEntryPort: UpdateProductEntryPort,
) : UpdateProductUseCase {
}