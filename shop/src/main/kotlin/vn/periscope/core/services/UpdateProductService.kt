package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.UpdateProductUseCase
import vn.periscope.ports.out.UpdateProductEntryPort

class UpdateProductService(
    private val transactionService: TransactionService,
    private val updateProductEntryPort: UpdateProductEntryPort,
) : UpdateProductUseCase {
}