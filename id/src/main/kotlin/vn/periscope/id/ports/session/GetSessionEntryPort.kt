package vn.periscope.id.ports.session

import vn.periscope.id.ports.session.models.SessionEntry
import java.util.*

interface GetSessionEntryPort {
    fun findById(id: UUID): SessionEntry
}