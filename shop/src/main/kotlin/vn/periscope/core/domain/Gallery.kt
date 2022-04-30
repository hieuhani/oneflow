package vn.periscope.core.domain

import kotlinx.datetime.Instant
import java.util.*

data class Gallery(
    val id: Long,
    val storeId: Long,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant
)