package vn.periscope.id.ports.user

import vn.periscope.id.ports.user.models.UserEntry

interface GetUserEntryPort {
    fun findByEmail(email: String): UserEntry?
}