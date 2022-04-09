package vn.periscope.cms.adapters.persistence.resource

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.UpdateBuilder

abstract class ResourceRepository<Entry, Entity, ID : Comparable<ID>, Table : IdTable<ID>> {
    abstract val table: Table
    abstract fun toSqlStatement(entry: Entry): Table.(UpdateBuilder<ID>) -> Unit
    abstract fun fromSqlResultRow(resultRow: ResultRow): Entity

    fun get(id: ID): Entity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    fun getAll(): List<Entity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }

    fun create(entry: Entry): Entity {
        val id = table.insert {
            toSqlStatement(entry)
        }[table.id]
        return get(id.value)!!
    }

//    fun update(entry: Entry): Entity {
//    }
}