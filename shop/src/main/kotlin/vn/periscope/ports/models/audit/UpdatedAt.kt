package vn.periscope.ports.models.audit

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class UpdatedAt(
   private val updatedAt: Instant
) {

    companion object {
        fun now() = UpdatedAt(Clock.System.now())

        fun of(updatedAt: Instant) = UpdatedAt(updatedAt)
    }
}
