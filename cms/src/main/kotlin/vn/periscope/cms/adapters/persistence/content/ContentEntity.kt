package vn.periscope.cms.adapters.persistence.content

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.cms.ports.content.models.ContentEntry

data class ContentEntity(
    val id: Long,
    val title: String,
    val description: String,
    val userId: Long,
    val contentTypeId: Long,
) {
    companion object
}

internal fun ContentEntry.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[ContentTable.title] = title
    it[ContentTable.description] = description
    it[ContentTable.userId] = userId
    it[ContentTable.contentTypeId] = contentTypeId
}

internal fun ContentEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = ContentEntity(
    id = resultRow[ContentTable.id].value,
    title = resultRow[ContentTable.title],
    description = resultRow[ContentTable.description],
    userId = resultRow[ContentTable.userId],
    contentTypeId = resultRow[ContentTable.contentTypeId],
)

internal fun ContentEntity.toEntry() = ContentEntry(
    id, title, description, userId, contentTypeId
)