package vn.periscope.id.ports.auth

import vn.periscope.id.ports.auth.models.GetUserPrincipalInput
import vn.periscope.id.ports.auth.models.UserPrincipal

interface GetUserPrincipalUseCase {
    suspend fun getUserPrincipal(command: GetUserPrincipalInput): UserPrincipal
}