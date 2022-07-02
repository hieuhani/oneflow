package vn.periscope.ports.models.id

import java.util.*

data class UUIDId(
    private val id: UUID
) {
    companion object {
        fun default() = UUIDId(UUID.randomUUID())
    }

    fun get(): UUID {
        return this.id
    }
}
