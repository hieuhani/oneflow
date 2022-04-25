package vn.periscope.adapters.persistence

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Sequence
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import vn.periscope.adapters.persistence.dao.GalleryTable
import vn.periscope.adapters.persistence.dao.ProductAttributeTable
import vn.periscope.adapters.persistence.dao.ProductTable
import javax.sql.DataSource

class DatabaseConnector(
    dataSource: DataSource,
) {
    private val db: Database = Database.connect(datasource = dataSource)
    private val tables = arrayOf(
        ProductTable ,
        GalleryTable,
        ProductAttributeTable
    )

    private val sequences = arrayOf(
        Sequence(
            name = "product_attribute_id_seq",
            startWith = 1,
            incrementBy = 1,
            minValue = 1,
            maxValue = 9223372036854775807,
            cycle = false,
            cache = 1
        ),
    )

    init {
        runBlocking {
            transaction(db) {
                SchemaUtils.create(*tables)
                SchemaUtils.createSequence(*sequences)
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