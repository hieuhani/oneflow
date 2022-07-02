package vn.periscope.adapters.persistence

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import vn.periscope.adapters.persistence.entity.*
import javax.sql.DataSource

class DatabaseConnector(
    dataSource: DataSource,
) {
    private val db: Database = Database.connect(datasource = dataSource)
    private val tables = arrayOf(
        ProductTable ,
        AttributeTable,
        ProductCategoryTable,
        AttributeValueTable,
        IndustryTable,
        BrandTable
    )

//    private val sequences = arrayOf(
//        ProductIdSequence.sequence,
//        AttributeIdSequence.sequence,
//        VariantIdSequence.sequence
//    )

    init {
        runBlocking {
            transaction(db) {
                SchemaUtils.create(*tables)
//                SchemaUtils.createSequence(*sequences)
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