package vn.periscope.core.services.product

import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.FilterProductUseCase
import vn.periscope.ports.product.out.FilterProductEntryPort

class FilterProductService(
    private val transactionService: TransactionService,
    private val filterProductEntryPort: FilterProductEntryPort,
) : FilterProductUseCase {
}