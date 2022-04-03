package vn.periscope.id.ports.user

import vn.periscope.id.ports.user.models.UserEntry

interface GetUserUseCase {
    suspend fun getUserById(userId: Long) : UserEntry
}