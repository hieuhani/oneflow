package vn.periscope.ports.models.audit

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class CreatedAt(
   private val createdAt: Instant
) {
    companion object {
        fun now() = CreatedAt(Clock.System.now())

        fun of(createdAt: Instant) = CreatedAt(createdAt)
    }

}
