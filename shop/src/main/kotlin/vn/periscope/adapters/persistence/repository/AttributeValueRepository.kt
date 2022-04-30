package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.AttributeValueEntity
import vn.periscope.adapters.persistence.entity.AttributeValueTable
import java.util.UUID

object AttributeValueRepository {

    private val table = AttributeValueTable

    private fun fromSqlResultRow(resultRow: ResultRow) = AttributeValueEntity(
        attributeNID = resultRow[AttributeValueTable.attributeNID],
        value = resultRow[AttributeValueTable.value]
    )

    fun delete(attributeNID: UUID, value: String) {
        table.deleteWhere {
            table.attributeNID eq attributeNID and (table.value eq value)
        }
    }

    fun delete(attributeNID: UUID) {
        table.deleteWhere {
            table.attributeNID eq attributeNID
        }
    }

    fun batchInsert(entities: List<AttributeValueEntity>) {
        table.batchInsert(entities) { entity ->
            this[AttributeValueTable.attributeNID] = entity.attributeNID
            this[AttributeValueTable.value] = entity.value
        }
    }

    fun findByAttributeIds(attributeNIDList: List<UUID>): List<AttributeValueEntity> {
        return table.select { table.attributeNID inList attributeNIDList }.map { fromSqlResultRow(it) }
    }
}