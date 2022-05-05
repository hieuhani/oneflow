package vn.periscope.id.adapters.persistence.session

import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import vn.periscope.id.ports.session.models.SessionEntry
import java.util.UUID

class SessionRepository {
    fun get(id: UUID): SessionEntity {
        return SessionTable.select { SessionTable.id eq id }.map { SessionEntity.fromSqlResultRow(it) }.first()
    }

    fun create(entry: SessionEntry): SessionEntity {
        val sessionId = SessionTable.insertAndGetId {
            entry.toCreateSqlStatement(it)
        }
        return get(sessionId.value)
    }
}