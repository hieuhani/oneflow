package vn.periscope.core.services.product

import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.SearchProductUseCase
import vn.periscope.ports.product.out.FilterProductEntryPort

class SearchProductService(
    private val transactionService: TransactionService,
    private val searchProductEntryPort: FilterProductEntryPort
) : SearchProductUseCase {}