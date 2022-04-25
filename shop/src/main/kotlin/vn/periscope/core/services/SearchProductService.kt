package vn.periscope.core.services

import vn.periscope.ports.TransactionService
import vn.periscope.ports.SearchProductUseCase
import vn.periscope.ports.out.FilterProductEntryPort

class SearchProductService(
    private val transactionService: TransactionService,
    private val searchProductEntryPort: FilterProductEntryPort
) : SearchProductUseCase {}