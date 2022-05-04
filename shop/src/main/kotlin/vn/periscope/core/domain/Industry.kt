package vn.periscope.core.domain

import kotlinx.datetime.Instant

data class Industry(
    val id: Long,
    val name: String,
    val machineName: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
