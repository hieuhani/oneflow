package vn.periscope.core.domain

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductType
import java.math.BigDecimal

data class Product(
    val id: Long = 0,
    val type: ProductType,
    val name: String,
    val photoId: Long = 0,
    val brandId: Long = 0,
    val industryId: Long = 0,
    val categoryIds: Set<Long> = setOf(),
    val price: BigDecimal,
    val createdAt: Instant,
    val updatedAt: Instant,
)



