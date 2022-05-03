package vn.periscope.cms.adapters.persistence.contenttype

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry
import vn.periscope.cms.ports.resource.models.FilterEntry

object ContentTypeRepository : ResourceRepository<ContentTypeEntry, ContentTypeEntity, Long, ContentTypeTable>() {
    override val table = ContentTypeTable

    override fun toInsertStatement(entry: ContentTypeEntry): ContentTypeTable.(InsertStatement<Number>) -> Unit = {
        it[name] = entry.name
        it[machineName] = entry.machineName
        it[description] = entry.description
    }

    override fun toUpdateStatement(entry: ContentTypeEntry): ContentTypeTable.(UpdateStatement) -> Unit = {
        it[name] = entry.name
        it[machineName] = entry.machineName
        it[description] = entry.description
    }

    override fun fromSqlResultRow(resultRow: ResultRow) = ContentTypeEntity(
        id = resultRow[ContentTypeTable.id].value,
        name = resultRow[ContentTypeTable.name],
        machineName = resultRow[ContentTypeTable.machineName],
        description = resultRow[ContentTypeTable.description],
    )

    override fun toFilterCondition(filter: FilterEntry): Op<Boolean> {
        return Op.TRUE
    }
}