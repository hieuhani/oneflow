package vn.periscope.adapters.api.rest.vm.request

import kotlinx.serialization.Serializable
import vn.periscope.ports.models.ProductEntry
import vn.periscope.share.statics.ProductType
import kotlin.streams.toList

@Serializable
data class CreateProductRequest(
    val type: ProductType,
    val name: String,
    val photoId: Long = 0,
    val brandId: Long = 0,
    val industryId: Long = 0,
    val categoryIds: Set<Long> = setOf(),
    val price: Double,
    val attributes: List<CreateAttributeRequest> = listOf()
)