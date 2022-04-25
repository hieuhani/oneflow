package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.ProductAttributeEntity
import vn.periscope.adapters.persistence.entity.ProductAttributeTable

object ProductAttributeRepository {
    private val table = ProductAttributeTable

    fun findById(id: Long): ProductAttributeEntity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = ProductAttributeEntity(
        id = resultRow[ProductAttributeTable.id].value,
        nid = resultRow[ProductAttributeTable.nid],
        businessId = resultRow[ProductAttributeTable.businessId],
        productId = resultRow[ProductAttributeTable.productId],
        name = resultRow[ProductAttributeTable.name],
        values = resultRow[ProductAttributeTable.values],
        createdAt = resultRow[ProductAttributeTable.createdAt],
        updatedAt = resultRow[ProductAttributeTable.updatedAt],
    )

    fun insert(entity: ProductAttributeEntity): ProductAttributeEntity {
        val id = table.insertAndGetId {
            it[nid] = entity.nid
            it[businessId] = entity.businessId
            it[productId] = entity.productId
            it[name] = entity.name
            it[values] = entity.values
            it[createdAt] = entity.createdAt
            it[updatedAt] = entity.createdAt
        }
        return findById(id.value)
    }

    fun update(id: Long, entity: ProductAttributeEntity): ProductAttributeEntity {
        val affectedRows = table.update({ table.id eq id }, null, {
            it[name] = entity.name
            it[values] = entity.values
            it[updatedAt] = entity.updatedAt
        })
        if (affectedRows == 1) {
            return findById(id)
        }
        throw RuntimeException("Product update failed")
    }

    fun delete(id: Long): Boolean {
        return table.deleteWhere {
            table.id eq id
        } == 1
    }

    fun createSequence() {
        val sequence = Sequence(
            name = "product_attribute_id_seq",
            startWith = 1,
            incrementBy = 1,
            minValue = 1,
            maxValue = 9223372036854775807,
            cycle = false,
            cache = 1
        )

        SchemaUtils.createSequence(sequence)
    }
}