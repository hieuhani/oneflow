package vn.periscope.id.ports.user

import vn.periscope.id.ports.user.models.UserEntry

interface CreateUserEntryPort {
    fun createUser(userEntry: UserEntry): UserEntry
}