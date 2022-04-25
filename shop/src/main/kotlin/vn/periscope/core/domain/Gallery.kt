package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.ports.product.models.GalleryEntry
import java.util.*

class Gallery private constructor(
    val id: Long,
    val nid: UUID,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object{
        fun create(entry: GalleryEntry): Gallery {
            return Gallery(
                id = 0,
                nid = UUID.randomUUID(),
                storeId = entry.storeId,
                default = entry.default,
                position = entry.position,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now(),
            )
        }
    }

}