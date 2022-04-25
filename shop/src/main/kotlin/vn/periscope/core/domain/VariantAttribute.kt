package vn.periscope.core.domain

import kotlinx.datetime.Instant

data class VariantAttribute(
    val id: Long? = 0,
    val name: String,
    val value: String,
    val createdAt: Instant,
    val updateAt: Instant
)
