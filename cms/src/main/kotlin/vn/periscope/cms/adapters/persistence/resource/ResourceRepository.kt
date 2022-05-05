package vn.periscope.cms.adapters.persistence.resource

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.ports.resource.models.FilterEntry
import vn.periscope.cms.ports.resource.models.Paging

abstract class ResourceRepository<Entry, Entity, ID : Comparable<ID>, Table : IdTable<ID>> {
    abstract val table: Table
    abstract fun toInsertStatement(entry: Entry): Table.(InsertStatement<Number>) -> Unit
    abstract fun toUpdateStatement(entry: Entry): Table.(UpdateStatement) -> Unit
    abstract fun fromSqlResultRow(resultRow: ResultRow): Entity
    abstract fun toFilterCondition(filter: FilterEntry): Op<Boolean>

    fun get(id: ID): Entity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    fun getAll(): List<Entity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }

    fun filter(filter: FilterEntry): Paging<Entity> {
        val count = table.select(toFilterCondition(filter)).count()
        val records = table
            .select(toFilterCondition(filter))
            .limit(filter.limit, offset = filter.offset.toLong())
            .map { fromSqlResultRow(it) }
        return Paging(
            records = records,
            limit = filter.limit,
            offset = filter.offset,
            totalRecords = count,
        )
    }

    fun create(entry: Entry): Entity {
        val id = table.insert(toInsertStatement(entry))[table.id]
        return get(id.value)!!
    }

    fun update(id: ID, entry: Entry): Entity {
        val affectedRows = table.update({ table.id eq id }, null, toUpdateStatement(entry))
        if (affectedRows == 1) {
            return get(id)
        }
        throw RuntimeException("Update failed")
    }

    fun delete(id: ID): Boolean {
        return table.deleteWhere {
            table.id eq id
        } == 1
    }
}
