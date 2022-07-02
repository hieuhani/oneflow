package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import vn.periscope.adapters.persistence.entity.AttributeEntity
import vn.periscope.adapters.persistence.entity.AttributeTable
import vn.periscope.adapters.persistence.entity.AttributeValueTable
import java.time.Instant

class AttributeRepository(
    private val attributeTable: AttributeTable,
    private val attributeValueTable: AttributeValueTable
) {

    fun getById(id: Long): AttributeEntity? {
         attributeTable.join(attributeValueTable, JoinType.INNER, additionalConstraint = {attributeTable.id eq attributeValueTable.attributeId} ).
         select {attributeTable.id eq id  }.groupBy(attributeTable.id)
    }

//    private fun fromSqlResultRow(resultRow: ResultRow) = AttributeEntity(
//        businessId = resultRow[attributeTable.businessId],
//        referType = resultRow[attributeTable.referType],
//        referId = resultRow[attributeTable.referId],
//        name = resultRow[attributeTable.name],
//        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[attributeTable.createdAt].toEpochMilli()),
//        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[attributeTable.updatedAt].toEpochMilli()),
//    )

    fun insert(entity: AttributeEntity): AttributeEntity? {
        val id = attributeTable.insertAndGetId {
            it[ownerNid] = entity.ownerNid
            it[name] = entity.name
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }

        return getById(id.value)
    }

//    fun update(id: Long, entity: AttributeEntity) {
//        table.update({ table.id eq id }, null, {
//            it[name] = entity.name
//            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
//        })
//    }

//    fun delete(id: Long) {
//        table.deleteWhere {
//            table.id eq id
//        }
//    }

    fun batchInsertValues(id: Long, values: Set<String>) {
        for (v in values) {
            attributeValueTable.insert {
                it[attributeId] = id
                it[value] = v
            }
        }
    }

//    fun findByReferIn(referType: AttributeReferType, referIds: List<Long>): List<AttributeEntity> {
//        return table.select { table.referType eq referType and (table.referId inList referIds) }
//            .map { fromSqlResultRow(it) }
//    }
//
//    fun findByRefer(referType: AttributeReferType, referId: Long): List<AttributeEntity> {
//        return table.select { table.referType eq referType and (table.referId eq referId) }
//            .map { fromSqlResultRow(it) }
//    }
}