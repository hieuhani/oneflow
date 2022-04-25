package vn.periscope.ports.product.models

import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy


class ProductEntry private constructor(
    val id: Long,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val name: String,
    val brandId: Long?,
    val industryId: Long?,
    val categoryIds: Set<Long>?,
    val galleries: List<GalleryEntry>?,
    val attributes: List<ProductAttributeEntry>?,
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
    )

    companion object {
        inline fun init(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var id: Long = 0
        var businessId: Long = 0
        var taxonomy: ProductTaxonomy = ProductTaxonomy.PHYSICAL
        var managementMethodology: ProductManagementMethodology = ProductManagementMethodology.NORMAL
        var name: String = ""
        var brandId: Long? = 0
        var industryId: Long? = 0
        var categoryIds: Set<Long>? = setOf()
        var galleries: List<GalleryEntry>? = listOf()
        var attributes: List<ProductAttributeEntry>? = listOf()
        fun build() = ProductEntry(this)
    }
}

