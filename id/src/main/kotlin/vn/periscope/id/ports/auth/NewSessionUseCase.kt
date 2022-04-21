package vn.periscope.id.ports.auth

import vn.periscope.id.ports.session.models.NewSessionPayload
import vn.periscope.id.ports.session.models.SessionEntry

interface NewSessionUseCase {
    suspend fun newSession(payload: NewSessionPayload): SessionEntry
}