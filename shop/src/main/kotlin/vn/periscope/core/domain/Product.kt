package vn.periscope.core.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.models.GalleryEntry
import vn.periscope.ports.models.ProductEntry
import vn.periscope.share.statics.ProductTaxonomy
import vn.periscope.share.statics.ProductType
import kotlin.streams.toList

data class Product(
    val id: Long,
    val taxonomy: ProductTaxonomy,
    val type: ProductType,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val categoryIds: Set<Long>,
    val galleries: List<Gallery>,
    val attributes: List<Attribute>,
    val createdAt: Instant,
    var updatedAt: Instant,
) {
    companion object
}

val Product.removeGalleries: List<Gallery>
    get() = listOf()

val Product.removeAttributes: List<Attribute>
    get() = listOf()

fun Product.addGallery(gallery: Gallery) {

}

fun Product.addAllGallery(galleries: List<Gallery>) {

}

fun Product.removeAll() {

}

fun Product.removeGallery(galleryId: Long) {

}

fun Product.modify() {
    this.updatedAt = Clock.System.now()
}

fun Product.toEntry(): ProductEntry {
    val galleries = galleries.stream().map {
        GalleryEntry(
            id = it.id,
            storeId = it.storeId,
            position = it.position
        )
    }.toList()

    val attributes = attributes.stream().map {
        AttributeEntry(
            id = it.id,
            name = it.name,
            values = it.values,
        )
    }.toList()

    return ProductEntry(
        id = id,
        taxonomy = taxonomy,
        type = type,
        name = name,
        brandId = brandId,
        industryId = industryId,
        categoryIds = categoryIds,
        galleries = galleries,
        attributes = attributes
    )
}

