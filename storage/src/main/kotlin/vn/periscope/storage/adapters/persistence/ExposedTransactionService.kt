package vn.periscope.storage.adapters.persistence

import vn.periscope.storage.ports.TransactionService

class ExposedTransactionService(
    private val dbConnector: DatabaseConnector,
) : TransactionService {
    override suspend fun <T> transaction(block: suspend () -> T) = dbConnector.transaction {
        block()
    }
}