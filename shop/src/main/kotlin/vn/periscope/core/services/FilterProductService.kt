package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.FilterProductUseCase
import vn.periscope.ports.out.FilterProductEntryPort

class FilterProductService(
    private val transactionService: TransactionService,
    private val filterProductEntryPort: FilterProductEntryPort,
) : FilterProductUseCase {
}