package vn.periscope.id.ports.session

import vn.periscope.id.ports.session.models.SessionEntry

interface GetSessionUseCase {
    suspend fun getSessionById(sessionId: String): SessionEntry
}