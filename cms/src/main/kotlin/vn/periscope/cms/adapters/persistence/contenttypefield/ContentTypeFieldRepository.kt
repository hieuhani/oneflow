package vn.periscope.cms.adapters.persistence.contenttypefield

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldFilterEntry
import vn.periscope.cms.ports.resource.models.FilterEntry

object ContentTypeFieldRepository : ResourceRepository<ContentTypeFieldEntry, ContentTypeFieldEntity, Long, ContentTypeFieldTable>() {
    override val table = ContentTypeFieldTable

    override fun toInsertStatement(entry: ContentTypeFieldEntry): ContentTypeFieldTable.(InsertStatement<Number>) -> Unit = {
        it[label] = entry.label
        it[name] = entry.name
        it[order] = entry.order
        it[required] = entry.required
        it[type] = entry.type
        it[cardinality] = entry.cardinality
        it[contentTypeId] = entry.contentTypeId
        it[extraData] = entry.extraData
    }

    override fun toUpdateStatement(entry: ContentTypeFieldEntry): ContentTypeFieldTable.(UpdateStatement) -> Unit = {
        it[label] = entry.label
        it[name] = entry.name
        it[order] = entry.order
        it[required] = entry.required
        it[type] = entry.type
        it[cardinality] = entry.cardinality
        it[contentTypeId] = entry.contentTypeId
        it[extraData] = entry.extraData
    }

    override fun fromSqlResultRow(resultRow: ResultRow) = ContentTypeFieldEntity(
        id = resultRow[ContentTypeFieldTable.id].value,
        label = resultRow[ContentTypeFieldTable.label],
        name = resultRow[ContentTypeFieldTable.name],
        order = resultRow[ContentTypeFieldTable.order],
        required = resultRow[ContentTypeFieldTable.required],
        type = resultRow[ContentTypeFieldTable.type],
        cardinality = resultRow[ContentTypeFieldTable.cardinality],
        contentTypeId = resultRow[ContentTypeFieldTable.contentTypeId],
        extraData = resultRow[ContentTypeFieldTable.extraData],
    )

    override fun toFilterCondition(filter: FilterEntry): Op<Boolean> {
        var op = Op.TRUE as Op<Boolean>
        if (filter is ContentTypeFieldFilterEntry) {
            filter.contentTypeId?.let {
                op = op.and {
                    table.contentTypeId eq filter.contentTypeId
                }
            }
        }
        return op
    }
}