package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.ports.CreateProductUseCase
import vn.periscope.ports.GetAttributeUseCase
import vn.periscope.ports.GetGalleryUseCase
import vn.periscope.ports.TransactionService
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.models.GalleryEntry
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.CreateProductEntryPort
import vn.periscope.ports.out.GetProductEntryPort

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort,
    private val getProductEntryPort: GetProductEntryPort,
    private val getGalleryUseCase: GetGalleryUseCase,
    private val getAttributeUseCase: GetAttributeUseCase
) : CreateProductUseCase {

    override suspend fun create(businessId: Long, entry: ProductEntry): Product = transactionService.transaction {
        val product = initProduct(entry)
        save(businessId, product)
        return@transaction product
    }

    private fun initProduct(entry: ProductEntry): Product {
        val galleries = initGalleries(entry.galleries)
        val attributes = initAttribute(entry.attributes)
        val id = getProductEntryPort.getNextSeriesId()
        return Product(
            id = id,
            taxonomy = entry.taxonomy,
            type = entry.type,
            name = entry.name,
            brandId = entry.brandId,
            industryId = entry.industryId,
            categoryIds = setOf(),
            galleries = galleries,
            attributes = attributes,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now(),
        )
    }

    private fun initGalleries(entries: List<GalleryEntry>?): List<Gallery> {
        if (entries.isNullOrEmpty()) return listOf()
        val ids = getGalleryUseCase.getNextSeriesIds(entries.size)
        val galleries = mutableListOf<Gallery>()
        entries.forEachIndexed { index, galleryEntry ->
            galleries.add(
                Gallery(
                    id = ids.toMutableList()[index],
                    storeId = galleryEntry.storeId,
                    position = galleryEntry.position,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            )
        }
        return galleries
    }

    private fun initAttribute(entries: List<AttributeEntry>?): List<Attribute> {
        if (entries.isNullOrEmpty()) return listOf()
        val ids = getAttributeUseCase.getNextSeriesIds(entries.size)
        val galleries = mutableListOf<Attribute>()
        entries.forEachIndexed { index, attributeEntry ->
            galleries.add(
                Attribute(
                    id = ids.toMutableList()[index],
                    name = attributeEntry.name,
                    values = attributeEntry.values,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            )
        }
        return galleries
    }

    private fun save(businessId: Long, product: Product) {
        return createProductEntryPort.insert(businessId, product)
    }
}