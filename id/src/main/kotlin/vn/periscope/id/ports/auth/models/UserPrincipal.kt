package vn.periscope.id.ports.auth.models

import io.ktor.server.auth.*

data class UserPrincipal(
    val userId: Long,
) : Principal