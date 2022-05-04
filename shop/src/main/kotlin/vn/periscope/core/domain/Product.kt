package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.models.ProductEntry
import vn.periscope.share.statics.ProductType
import kotlin.streams.toList

data class Product(
    val id: Long,
    val type: ProductType,
    var name: String,
    var photoId: Long,
    var brandId: Long,
    var industryId: Long,
    var categoryIds: Set<Long>,
    var attributes: List<Attribute>,
    val createdAt: Instant,
    var updatedAt: Instant,
) {
    var removeAttributes: ArrayList<Attribute> = ArrayList()

    fun modify() {
        this.updatedAt = Clock.System.now()
    }

    fun toEntry(): ProductEntry {

        val attributes = attributes.stream().map {
            AttributeEntry(
                id = it.id,
                name = it.name,
                values = it.values,
            )
        }.toList()

        return ProductEntry(
            id = id,
            type = type,
            name = name,
            photoId = photoId,
            brandId = brandId,
            industryId = industryId,
            categoryIds = categoryIds,
            attributes = attributes
        )
    }
}



