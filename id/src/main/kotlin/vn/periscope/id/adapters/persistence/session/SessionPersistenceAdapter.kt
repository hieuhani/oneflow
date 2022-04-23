package vn.periscope.id.adapters.persistence.session

import vn.periscope.id.ports.session.CreateSessionEntryPort
import vn.periscope.id.ports.session.GetSessionEntryPort
import vn.periscope.id.ports.session.models.SessionEntry
import java.util.UUID

internal class SessionPersistenceAdapter(
    private val sessionRepository: SessionRepository,
) : CreateSessionEntryPort,
    GetSessionEntryPort {
    override fun createSession(sessionEntry: SessionEntry): SessionEntry {
        return sessionRepository.create(sessionEntry).toEntry()
    }

    override fun findById(id: UUID): SessionEntry {
        return sessionRepository.get(id).toEntry()
    }
}