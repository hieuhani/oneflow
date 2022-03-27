package vn.periscope.storage.adapters.persistence

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransaction
import vn.periscope.storage.adapters.persistence.file.FileTable
import javax.sql.DataSource

class DatabaseConnector(
    dataSource: DataSource,
) {
    private val db: Database = Database.connect(datasource = dataSource)
    private val tables = arrayOf(
        FileTable,
    )

    init {
        runBlocking {
            org.jetbrains.exposed.sql.transactions.transaction(db) {
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