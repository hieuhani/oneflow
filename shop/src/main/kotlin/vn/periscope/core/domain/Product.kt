package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy
import java.util.*

data class Product(
    val id: Long,
    val nid: UUID,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val name: String,
    val brandId: Long? = 0,
    val industryId: Long? = 0,
    val categoryIds: Set<Long>? = setOf(),
    val galleries: List<Gallery>? = listOf(),
    val attributes: List<ProductAttribute>? = listOf(),
    val createdAt: Instant,
    var updatedAt: Instant,
) {
    val removeGalleries: List<Gallery> = listOf()
    val removeAttributes: List<ProductAttribute> = listOf()


    fun addGallery(gallery: Gallery) {

    }

    fun addAllGallery(galleries: List<Gallery>) {

    }

    fun removeAll() {

    }

    fun removeGallery(galleryId: Long) {

    }

    fun modify() {
        this.updatedAt = Clock.System.now()
    }
}

