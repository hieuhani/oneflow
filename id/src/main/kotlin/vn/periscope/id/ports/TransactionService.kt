package vn.periscope.id.ports

interface TransactionService {
    suspend fun <T> transaction(block: suspend () -> T): T
}
