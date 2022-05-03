package vn.periscope.cms.adapters.persistence.taxonomy

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.resource.models.FilterEntry
import vn.periscope.cms.ports.taxonomy.models.TaxonomyEntry

object TaxonomyRepository : ResourceRepository<TaxonomyEntry, TaxonomyEntity, Long, TaxonomyTable>() {
    override val table = TaxonomyTable

    override fun toInsertStatement(entry: TaxonomyEntry): TaxonomyTable.(InsertStatement<Number>) -> Unit = {
        it[name] = entry.name
        it[machineName] = entry.machineName
        it[description] = entry.description
    }

    override fun toUpdateStatement(entry: TaxonomyEntry): TaxonomyTable.(UpdateStatement) -> Unit = {
        it[name] = entry.name
        it[machineName] = entry.machineName
        it[description] = entry.description
    }

    override fun fromSqlResultRow(resultRow: ResultRow) = TaxonomyEntity(
        id = resultRow[TaxonomyTable.id].value,
        name = resultRow[TaxonomyTable.name],
        machineName = resultRow[TaxonomyTable.machineName],
        description = resultRow[TaxonomyTable.description],
    )

    override fun toFilterCondition(filter: FilterEntry): Op<Boolean> {
        return Op.TRUE
    }
}