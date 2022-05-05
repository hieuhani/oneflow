package vn.periscope.id.adapters.persistence.session

import kotlinx.datetime.Instant
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.id.ports.session.models.SessionEntry
import java.util.UUID

data class SessionEntity(
    val id: UUID,
    val userId: Long,
    val issuedAt: Instant,
    val expiresAt: Instant,
    val refreshToken: String,
) {
    companion object
}

internal fun SessionEntry.toCreateSqlStatement(statement: InsertStatement<EntityID<UUID>>) = statement.let {
    it[SessionTable.userId] = userId
    it[SessionTable.issuedAt] = issuedAt
    it[SessionTable.expiresAt] = expiresAt
    it[SessionTable.refreshToken] = refreshToken
}

internal fun SessionEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = SessionEntity(
    id = resultRow[SessionTable.id].value,
    userId = resultRow[SessionTable.userId],
    issuedAt = resultRow[SessionTable.issuedAt],
    expiresAt = resultRow[SessionTable.expiresAt],
    refreshToken = resultRow[SessionTable.refreshToken],
)

internal fun SessionEntity.toEntry() = SessionEntry(
    id,
    userId,
    issuedAt,
    expiresAt,
    refreshToken,
)