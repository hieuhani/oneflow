package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.IndustryEntity
import vn.periscope.adapters.persistence.entity.IndustryTable
import java.time.Instant

class IndustryRepository(
    private val table: IndustryTable
) {

    private fun findById(id: Long): IndustryEntity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    fun findById(businessId: Long, id: Long): IndustryEntity {
        return table.select { table.id eq id and (table.businessId eq businessId) }.first().let { fromSqlResultRow(it) }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = IndustryEntity(
        id = resultRow[IndustryTable.id].value,
        businessId = resultRow[IndustryTable.businessId],
        name = resultRow[IndustryTable.name],
        machineName = resultRow[IndustryTable.machineName],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[IndustryTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[IndustryTable.updatedAt].toEpochMilli()),
    )

    fun insert(entity: IndustryEntity): IndustryEntity {
        val id = table.insertAndGetId {
            it[businessId] = entity.businessId
            it[name] = entity.name
            it[machineName] = entity.machineName
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
        return findById(id.value)
    }

    fun update(id: Long, entity: IndustryEntity) {
        table.update({ table.id eq id }, null, {
            it[name] = entity.name
            it[machineName] = entity.machineName
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        })
    }

    fun delete(id: Long) {
        table.deleteWhere {
            table.id eq id
        }
    }

    fun findAll(): List<IndustryEntity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }
}