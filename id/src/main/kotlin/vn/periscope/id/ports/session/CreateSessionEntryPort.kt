package vn.periscope.id.ports.session

import vn.periscope.id.ports.session.models.SessionEntry

interface CreateSessionEntryPort {
    fun createSession(sessionEntry: SessionEntry): SessionEntry
}