package vn.periscope.cms.adapters.persistence

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import vn.periscope.cms.adapters.persistence.content.ContentTable
import vn.periscope.cms.adapters.persistence.contentfieldvalue.ContentFieldValueTable
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeTable
import vn.periscope.cms.adapters.persistence.contenttypefield.ContentTypeFieldTable
import vn.periscope.cms.adapters.persistence.taxonomy.TaxonomyTable
import vn.periscope.cms.adapters.persistence.taxonomyterm.TaxonomyTermTable

import javax.sql.DataSource

class DatabaseConnector(
    dataSource: DataSource,
) {
    private val db: Database = Database.connect(datasource = dataSource)
    private val tables = arrayOf(
        TaxonomyTable,
        TaxonomyTermTable,
        ContentTypeTable,
        ContentTable,
        ContentTypeFieldTable,
        ContentFieldValueTable,
    )

    init {
        runBlocking {
            transaction(db) {
                SchemaUtils.createMissingTablesAndColumns(*tables)
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