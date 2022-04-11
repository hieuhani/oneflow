package vn.periscope.adapters.persistence

import kotlinx.coroutines.runBlocking
import javax.sql.DataSource

class DatabaseConnector(
    dataSource: DataSource,
) {
    private val db: Database = Database.connect(datasource = dataSource)
    private val tables = arrayOf(
        UserTable,
        BusinessTable,
    )

    init {
        runBlocking {
            transaction(db) {
                SchemaUtils.create(*tables)
            }
        }
    }

    suspend fun <T> transaction(block: suspend (tx: Transaction) -> T): T {
        val tx = TransactionManager.currentOrNull()
        return if (tx == null || tx.connection.isClosed) {
            newSuspendedTransaction(db = db) {
                block(this)
            }
        } else {
            tx.suspendedTransaction {
                block(this)
            }
        }
    }
}