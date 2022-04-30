package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.AttributeEntity
import vn.periscope.adapters.persistence.entity.AttributeTable
import java.time.Instant
import java.util.UUID

object AttributeRepository {
    private val table = AttributeTable

    fun findById(id: Long): AttributeEntity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = AttributeEntity(
        id = resultRow[AttributeTable.id].value,
        businessId = resultRow[AttributeTable.businessId],
        referNID = resultRow[AttributeTable.referNID],
        name = resultRow[AttributeTable.name],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[AttributeTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[AttributeTable.updatedAt].toEpochMilli()),
        nid = resultRow[AttributeTable.nid]
    )

    fun insert(entity: AttributeEntity) {
        table.insert {
            it[id] = entity.id
            it[businessId] = entity.businessId
            it[referNID] = entity.referNID
            it[name] = entity.name
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
    }

    fun update(id: Long, entity: AttributeEntity) {
        table.update({ table.id eq id }, null, {
            it[name] = entity.name
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        })
    }

    fun delete(id: Long): Boolean {
        return table.deleteWhere {
            table.id eq id
        } == 1
    }

    fun batchInsert(entities: List<AttributeEntity>) {
        table.batchInsert(entities) { entity ->
            this[AttributeTable.id] = entity.id
            this[AttributeTable.businessId] = entity.businessId
            this[AttributeTable.referNID] = entity.referNID
            this[AttributeTable.name] = entity.name
            this[AttributeTable.createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            this[AttributeTable.updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
    }

    fun findByRefer(referNIDList: List<UUID>): List<AttributeEntity> {
        return table.select { table.referNID inList referNIDList }
            .map { fromSqlResultRow(it) }
    }

    fun findByRefer(referNID: UUID): List<AttributeEntity> {
        return table.select { table.referNID eq referNID }
            .map { fromSqlResultRow(it) }
    }
}