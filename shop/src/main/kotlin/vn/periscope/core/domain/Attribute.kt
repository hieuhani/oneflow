package vn.periscope.core.domain

import kotlinx.datetime.Instant
import java.util.UUID

data class Attribute (
    val id: Long,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    val removeValues: Set<String> = setOf()
}

