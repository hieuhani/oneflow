package vn.periscope.cms.ports

interface TransactionService {
    suspend fun <T> transaction(block: suspend () -> T): T
}
