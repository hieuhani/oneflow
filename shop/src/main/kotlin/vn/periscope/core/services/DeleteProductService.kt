package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.DeleteProductUseCase
import vn.periscope.ports.out.DeleteProductEntryPort

class DeleteProductService(
    private val transactionService: TransactionService,
    private val deleteProductEntryPort: DeleteProductEntryPort,
) : DeleteProductUseCase {

}