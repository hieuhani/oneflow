package vn.periscope.id.adapters.persistence

import vn.periscope.id.ports.TransactionService

class ExposedTransactionService(
    private val dbConnector: DatabaseConnector,
) : TransactionService {
    override suspend fun <T> transaction(block: suspend () -> T) = dbConnector.transaction {
        block()
    }
}