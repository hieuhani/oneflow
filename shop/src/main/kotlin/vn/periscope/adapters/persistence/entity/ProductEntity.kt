package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductType
import java.util.*

data class ProductEntity(
    val id: Long = 0,
    val businessId: Long,
    val type: ProductType,
    val name: String,
    val photoId: Long,
    val brandId: Long,
    val industryId: Long,
    val price: Double,
    val createdAt: Instant,
    val updatedAt: Instant,
    val nid: UUID
)