package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.ProductAttributeEntity
import vn.periscope.adapters.persistence.entity.ProductAttributeTable
import java.time.Instant

object ProductAttributeRepository {
    private val table = ProductAttributeTable

    private const val SEPARATOR = ","

    fun findById(id: Long): ProductAttributeEntity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = ProductAttributeEntity(
        id = resultRow[ProductAttributeTable.id].value,
        nid = resultRow[ProductAttributeTable.nid],
        businessId = resultRow[ProductAttributeTable.businessId],
        productId = resultRow[ProductAttributeTable.productId],
        name = resultRow[ProductAttributeTable.name],
        values = splitToSet(resultRow[ProductAttributeTable.values]),
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductAttributeTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductAttributeTable.updatedAt].toEpochMilli()),
    )

    private fun splitToSet(values: String): Set<String> {
        if (values.isBlank()) {
            return emptySet()
        }
        return values.split(SEPARATOR).toSet()
    }

    fun insert(entity: ProductAttributeEntity): ProductAttributeEntity {
        val id = table.insertAndGetId {
            it[nid] = entity.nid
            it[businessId] = entity.businessId
            it[productId] = entity.productId
            it[name] = entity.name
            it[values] = entity.values.joinToString(SEPARATOR)
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
        return findById(id.value)
    }

    fun update(id: Long, entity: ProductAttributeEntity): ProductAttributeEntity {
        val affectedRows = table.update({ table.id eq id }, null, {
            it[name] = entity.name
            it[values] = entity.values.joinToString(SEPARATOR)
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
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

    fun batchInsert(entities: List<ProductAttributeEntity>) {
        table.batchInsert(entities) { entity ->
            this[ProductAttributeTable.id] = entity.id
            this[ProductAttributeTable.nid] = entity.nid
            this[ProductAttributeTable.businessId] = entity.businessId
            this[ProductAttributeTable.productId] = entity.productId
            this[ProductAttributeTable.name] = entity.name
            this[ProductAttributeTable.values] = entity.values.joinToString(SEPARATOR)
            this[ProductAttributeTable.createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            this[ProductAttributeTable.updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
    }

    fun findByProductId(productId: Long): List<ProductAttributeEntity> {
        return table.select { table.productId eq productId }.map { fromSqlResultRow(it) }
    }
}