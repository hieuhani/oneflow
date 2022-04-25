package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.ports.product.models.GalleryEntry
import java.util.*

class Gallery internal constructor(
    val id: Long,
    val nid: UUID,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant
)