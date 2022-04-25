package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.ports.product.models.ProductAttributeEntry
import java.util.UUID

class ProductAttribute internal constructor(
    val id: Long,
    val nid: UUID,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    val removeValues: Set<String> = setOf()
}

