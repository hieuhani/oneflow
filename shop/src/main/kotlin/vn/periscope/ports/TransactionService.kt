package vn.periscope.ports

interface TransactionService {
    suspend fun <T> transaction(block: suspend () -> T): T
}
