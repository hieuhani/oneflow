package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.ports.product.models.ProductEntry
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy
import java.util.*
import kotlin.streams.toList

class Product private constructor(
    var id: Long,
    var nid: UUID,
    var businessId: Long,
    var taxonomy: ProductTaxonomy,
    var managementMethodology: ProductManagementMethodology,
    var name: String,
    var brandId: Long,
    var industryId: Long,
    var categories: List<Category>,
    var galleries: List<Gallery>,
    var attributes: List<ProductAttribute>,
    var createdAt: Instant,
    var updatedAt: Instant
) {
    val removeGalleries: List<Gallery> = listOf()
    val removeAttributes: List<ProductAttribute> = listOf()

    companion object {
        fun create(entry: ProductEntry): Product {
            val galleries = entry.galleries.orEmpty().stream()
                .map { Gallery.create(it) }
                .toList()
            val attributes = entry.attributes.orEmpty().stream()
                .map { ProductAttribute.create(it) }
                .toList()
            return Product(
                id = entry.id,
                nid = UUID.randomUUID(),
                businessId = entry.businessId,
                taxonomy = entry.taxonomy,
                managementMethodology = entry.managementMethodology,
                name = entry.name,
                brandId = entry.brandId ?: 0,
                industryId = entry.industryId ?: 0,
                categories = listOf(),
                galleries = galleries,
                attributes = attributes,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            )
        }
    }

    fun addGallery(gallery: Gallery) {

    }

    fun addAllGallery(galleries: List<Gallery>) {

    }

    fun removeAll() {
        this.removeGalleries.toMutableList().addAll(galleries)
        this.galleries.toMutableList().removeAll(galleries)
        modify()
    }

    fun removeGallery(galleryId: Long) {

    }

    fun modify() {
        this.updatedAt = Clock.System.now()
    }
}

