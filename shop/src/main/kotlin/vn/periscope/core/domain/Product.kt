package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.share.statics.ProductManagementMethodology
import vn.periscope.share.statics.ProductTaxonomy
import java.util.*

class Product internal constructor(
    var id: Long,
    var nid: UUID,
    var businessId: Long,
    var taxonomy: ProductTaxonomy,
    var managementMethodology: ProductManagementMethodology,
    var name: String,
    var brandId: Long,
    var industryId: Long,
    var categoryIds: Set<Long>,
    var galleries: List<Gallery>,
    var attributes: List<ProductAttribute>,
    var createdAt: Instant,
    var updatedAt: Instant
) {
    val removeGalleries: List<Gallery> = listOf()
    val removeAttributes: List<ProductAttribute> = listOf()


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

