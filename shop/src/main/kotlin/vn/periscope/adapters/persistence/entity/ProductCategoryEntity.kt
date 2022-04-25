package vn.periscope.adapters.persistence.entity

import kotlinx.datetime.Instant

data class ProductCategoryEntity(
    val id: Long? = 0,
    val productId: Long,
    val categoryId: Long,
    val createdAt: Instant

)
