package vn.periscope.cms.adapters.persistence.content

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import vn.periscope.cms.adapters.persistence.resource.ResourceRepository
import vn.periscope.cms.ports.content.models.ContentEntry

object ContentRepository : ResourceRepository<ContentEntry, ContentEntity, Long, ContentTable>() {
    override val table = ContentTable

    override fun toSqlStatement(entry: ContentEntry): ContentTable.(UpdateBuilder<Long>) -> Unit = {
        it[title] = entry.title
        it[description] = entry.description
        it[userId] = entry.userId
        it[contentTypeId] = entry.contentTypeId
    }

    override fun fromSqlResultRow(resultRow: ResultRow) = ContentEntity(
        id = resultRow[ContentTable.id].value,
        title = resultRow[ContentTable.title],
        description = resultRow[ContentTable.description],
        userId = resultRow[ContentTable.userId],
        contentTypeId = resultRow[ContentTable.contentTypeId],
    )
}