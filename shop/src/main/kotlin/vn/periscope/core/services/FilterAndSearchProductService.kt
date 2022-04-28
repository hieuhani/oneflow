package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.FilterAndSearchProductUseCase
import vn.periscope.ports.out.FilterAndSearchProductEntryPort

class FilterAndSearchProductService(
    private val transactionService: TransactionService,
    private val filterAndSearchProductEntryPort: FilterAndSearchProductEntryPort,
) : FilterAndSearchProductUseCase {
}