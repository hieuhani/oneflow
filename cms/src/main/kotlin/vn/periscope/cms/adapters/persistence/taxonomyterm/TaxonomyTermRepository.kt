package vn.periscope.cms.adapters.persistence.taxonomyterm

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.taxonomyterm.models.TaxonomyTermEntry

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
}