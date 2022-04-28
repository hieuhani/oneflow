package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.out.FilterAndSearchProductEntryPort

class SearchProductService(
    private val transactionService: TransactionService,
    private val searchProductEntryPort: FilterAndSearchProductEntryPort
) : SearchProductUseCase {}