package vn.periscope.id.adapters.persistence.session

import vn.periscope.id.ports.session.CreateSessionEntryPort
import vn.periscope.id.ports.session.models.SessionEntry

internal class SessionPersistenceAdapter(
    private val sessionRepository: SessionRepository,
) : CreateSessionEntryPort {
    override fun createSession(sessionEntry: SessionEntry): SessionEntry {
        return sessionRepository.create(sessionEntry).toEntry()
    }
}