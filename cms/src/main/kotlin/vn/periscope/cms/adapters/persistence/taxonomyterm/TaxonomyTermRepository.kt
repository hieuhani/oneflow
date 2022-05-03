package vn.periscope.cms.adapters.persistence.taxonomyterm

import io.ktor.util.reflect.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.resource.models.FilterEntry
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermFilterEntry

object TaxonomyTermRepository : ResourceRepository<TaxonomyTermEntry, TaxonomyTermEntity, Long, TaxonomyTermTable>() {
    override val table = TaxonomyTermTable

    override fun toInsertStatement(entry: TaxonomyTermEntry): TaxonomyTermTable.(InsertStatement<Number>) -> Unit = {
        it[name] = entry.name
        it[machineName] = entry.machineName
        it[description] = entry.description
        it[taxonomyId] = entry.taxonomyId
        it[parentId] = entry.parentId
    }

    override fun toUpdateStatement(entry: TaxonomyTermEntry): TaxonomyTermTable.(UpdateStatement) -> Unit = {
        it[name] = entry.name
        it[machineName] = entry.machineName
        it[description] = entry.description
        it[taxonomyId] = entry.taxonomyId
        it[parentId] = entry.parentId
    }

    override fun fromSqlResultRow(resultRow: ResultRow) = TaxonomyTermEntity(
        id = resultRow[TaxonomyTermTable.id].value,
        name = resultRow[TaxonomyTermTable.name],
        machineName = resultRow[TaxonomyTermTable.machineName],
        description = resultRow[TaxonomyTermTable.description],
        taxonomyId = resultRow[TaxonomyTermTable.taxonomyId],
        parentId = resultRow[TaxonomyTermTable.parentId],
    )

    override fun toFilterCondition(filter: FilterEntry): Op<Boolean> {
        var op = Op.TRUE as Op<Boolean>
        if (filter is TaxonomyTermFilterEntry) {
            filter.taxonomyId?.let {
                op = op.and {
                    table.taxonomyId eq filter.taxonomyId
                }
            }

            filter.machineName?.let {
                op = op.and {
                    table.machineName eq filter.machineName
                }
            }

            filter.parentId?.let {
                op = op.and {
                    table.parentId eq filter.parentId
                }
            }
        }
        return op
    }

}