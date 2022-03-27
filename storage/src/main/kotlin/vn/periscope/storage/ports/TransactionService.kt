package vn.periscope.storage.ports

interface TransactionService {
    suspend fun <T> transaction(block: suspend () -> T): T
}
