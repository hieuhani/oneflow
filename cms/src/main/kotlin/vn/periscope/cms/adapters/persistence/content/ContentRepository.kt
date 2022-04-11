package vn.periscope.cms.adapters.persistence.content

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.content.models.ContentEntry

object ContentRepository : ResourceRepository<ContentEntry, ContentEntity, Long, ContentTable>() {
    override val table = ContentTable

    override fun fromSqlResultRow(resultRow: ResultRow) = ContentEntity(
        id = resultRow[ContentTable.id].value,
        title = resultRow[ContentTable.title],
        description = resultRow[ContentTable.description],
        userId = resultRow[ContentTable.userId],
        contentTypeId = resultRow[ContentTable.contentTypeId],
    )

    override fun toInsertStatement(entry: ContentEntry): ContentTable.(InsertStatement<Number>) -> Unit = {
        it[title] = entry.title
        it[description] = entry.description
        if (entry.userId != null) {
            it[userId] = entry.userId
        }
        it[contentTypeId] = entry.contentTypeId
    }

    override fun toUpdateStatement(entry: ContentEntry): ContentTable.(UpdateStatement) -> Unit = {
        it[title] = entry.title
        it[description] = entry.description
        it[contentTypeId] = entry.contentTypeId
    }
}