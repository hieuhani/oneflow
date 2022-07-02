package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.models.ProductEntry
import vn.periscope.share.statics.ProductType

@Serializable
data class UpdateProductRequest(
    val type: ProductType,
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val photoId: Long? = 0,
    val attributes: List<UpdateAttributeRequest>? = listOf(),
    val price: Double,
) {

    fun toEntry(): ProductEntry {
        val attributeEntries = toAttributeEntry()
        return ProductEntry(
            type = type,
            name = name,
            photo = photoId ?: 0,
            brand = brandId ?: 0,
            industry = industryId ?: 0,
            categoryColection = categoryIds ?: setOf(),
            attributes = attributeEntries,
            price = price
        )
    }

    private fun toAttributeEntry(): List<AttributeEntry> {
        if (attributes.isNullOrEmpty()) return emptyList()
        val entries = mutableListOf<AttributeEntry>()
        for (attribute in attributes) {
            val entry = AttributeEntry(
                id = attribute.id,
                name = attribute.name,
                values = attribute.values
            )
            entries.add(entry)
        }
        return entries
    }
}
