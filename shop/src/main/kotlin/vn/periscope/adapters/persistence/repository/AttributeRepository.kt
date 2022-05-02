package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.AttributeEntity
import vn.periscope.adapters.persistence.entity.AttributeTable
import vn.periscope.share.statics.AttributeReferType
import java.time.Instant

class AttributeRepository(
    private val table: AttributeTable
) {
    private fun fromSqlResultRow(resultRow: ResultRow) = AttributeEntity(
        id = resultRow[AttributeTable.id],
        businessId = resultRow[AttributeTable.businessId],
        referType = resultRow[AttributeTable.referType],
        referId = resultRow[AttributeTable.referId],
        name = resultRow[AttributeTable.name],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[AttributeTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[AttributeTable.updatedAt].toEpochMilli()),
        nid = resultRow[AttributeTable.nid]
    )

    fun insert(entity: AttributeEntity) {
        table.insert {
            it[id] = entity.id
            it[businessId] = entity.businessId
            it[referType] = entity.referType
            it[referId] = entity.referId
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

    fun delete(id: Long) {
        table.deleteWhere {
            table.id eq id
        }
    }

    fun batchInsert(entities: List<AttributeEntity>) {
        table.batchInsert(entities) { entity ->
            this[AttributeTable.id] = entity.id
            this[AttributeTable.businessId] = entity.businessId
            this[AttributeTable.referType] = entity.referType
            this[AttributeTable.referId] = entity.referId
            this[AttributeTable.name] = entity.name
            this[AttributeTable.createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            this[AttributeTable.updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
            this[AttributeTable.nid] = entity.nid
        }
    }

    fun findByReferIn(referType: AttributeReferType, referIds: List<Long>): List<AttributeEntity> {
        return table.select { table.referType eq referType and (table.referId inList referIds) }
            .map { fromSqlResultRow(it) }
    }

    fun findByRefer(referType: AttributeReferType, referId: Long): List<AttributeEntity> {
        return table.select { table.referType eq referType and (table.referId eq referId) }
            .map { fromSqlResultRow(it) }
    }
}