package vn.periscope.cms.adapters.persistence.contentfieldvalue

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry

object ContentFieldValueRepository : ResourceRepository<ContentFieldValueEntry, ContentFieldValueEntity, Long, ContentFieldValueTable>() {
    override val table = ContentFieldValueTable

    override fun toInsertStatement(entry: ContentFieldValueEntry): ContentFieldValueTable.(InsertStatement<Number>) -> Unit = {
        it[value] = entry.value
        it[contentId] = entry.contentId
        it[contentTypeFieldId] = entry.contentTypeFieldId
    }

    override fun toUpdateStatement(entry: ContentFieldValueEntry): ContentFieldValueTable.(UpdateStatement) -> Unit = {
        it[value] = entry.value
        it[contentId] = entry.contentId
        it[contentTypeFieldId] = entry.contentTypeFieldId
    }

    override fun fromSqlResultRow(resultRow: ResultRow): ContentFieldValueEntity = ContentFieldValueEntity(
        id = resultRow[ContentFieldValueTable.id].value,
        value = resultRow[ContentFieldValueTable.value],
        contentId = resultRow[ContentFieldValueTable.contentId],
        contentTypeFieldId = resultRow[ContentFieldValueTable.contentTypeFieldId],
    )
}