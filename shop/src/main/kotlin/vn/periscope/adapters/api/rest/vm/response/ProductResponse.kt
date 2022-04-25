package vn.periscope.adapters.api.rest.vm.response

import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy

@kotlinx.serialization.Serializable
class ProductResponse private constructor(
    val id: Long?,
    val businessId: Long?,
    val taxonomy: ProductTaxonomy?,
    val managementMethodology: ProductManagementMethodology?,
    val name: String?,
    val brandId: Long?,
    val industryId: Long?,
    val categoryIds: Set<Long>?,
    val galleries: List<GalleryResponse>?,
    val attributes: List<ProductAttributeResponse>?,
    val createdAt: Instant?,
    val updatedAt: Instant?
) {
    private constructor(builder: Builder) : this(
        builder.id,
        builder.businessId,
        builder.taxonomy,
        builder.managementMethodology,
        builder.name,
        builder.brandId,
        builder.industryId,
        builder.categoryIds,
        builder.galleries,
        builder.attributes,
        builder.createdAt,
        builder.updatedAt,
    )

    companion object {
        inline fun init(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var id: Long? = null
        var businessId: Long? = null
        var taxonomy: ProductTaxonomy? = null
        var managementMethodology: ProductManagementMethodology? = null
        var name: String? = null
        var brandId: Long? = null
        var industryId: Long? = null
        var categoryIds: Set<Long>? = setOf()
        var galleries: List<GalleryResponse>? = listOf()
        var attributes: List<ProductAttributeResponse>? = listOf()
        var createdAt: Instant? = null
        var updatedAt: Instant? = null
        fun build() = ProductResponse(this)
    }

}
