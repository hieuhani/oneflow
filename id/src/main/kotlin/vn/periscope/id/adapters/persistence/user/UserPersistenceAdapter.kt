package vn.periscope.id.adapters.persistence.user

import vn.periscope.id.ports.user.CreateUserEntryPort
import vn.periscope.id.ports.user.GetUserEntryPort
import vn.periscope.id.ports.user.models.UserEntry

internal class UserPersistenceAdapter(
    private val userRepository: UserRepository,
) : CreateUserEntryPort,
    GetUserEntryPort {
    override fun createUser(userEntry: UserEntry): UserEntry {
        return userRepository.create(userEntry).toEntry()
    }

    override fun findByEmail(email: String): UserEntry? {
        return userRepository.findByEmail(email)?.toEntry()
    }

    override fun findById(id: Long): UserEntry {
        return userRepository.findById(id).toEntry()
    }
}