package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.AttributeValueTable

class AttributeValueRepository(
    private val table: AttributeValueTable
) {
    private fun fromSqlResultRow(resultRow: ResultRow) = AttributeValueEntity(
        attributeId = resultRow[AttributeValueTable.attributeId],
        value = resultRow[AttributeValueTable.value]
    )

    fun delete(attributeId: Long, value: String) {
        table.deleteWhere {
            table.attributeId eq attributeId and (table.value eq value)
        }
    }

    fun delete(attributeId: Long) {
        table.deleteWhere {
            table.attributeId eq attributeId
        }
    }

    fun batchInsert(entities: List<AttributeValueEntity>) {
        table.batchInsert(entities) { entity ->
            this[AttributeValueTable.attributeId] = entity.attributeId
            this[AttributeValueTable.value] = entity.value
        }
    }

    fun findByAttributeIdIn(attributeIds: List<Long>): List<AttributeValueEntity> {
        return table.select { table.attributeId inList attributeIds }.map { fromSqlResultRow(it) }
    }
}