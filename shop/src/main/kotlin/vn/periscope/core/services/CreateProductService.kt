package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.core.domain.ProductAttribute
import vn.periscope.ports.TransactionService
import vn.periscope.ports.CreateProductUseCase
import vn.periscope.ports.GetGalleryUseCase
import vn.periscope.ports.GetProductAttributeUseCase
import vn.periscope.ports.models.GalleryEntry
import vn.periscope.ports.models.ProductAttributeEntry
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.CreateProductEntryPort
import vn.periscope.ports.out.GetProductEntryPort
import java.util.*

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort,
    private val getProductEntryPort: GetProductEntryPort,
    private val getGalleryUseCase: GetGalleryUseCase,
    private val getProductAttributeUseCase: GetProductAttributeUseCase
) : CreateProductUseCase {

    override suspend fun create(entry: ProductEntry): Product {
        val galleries = createGalleries(entry.galleries)
        val attributes = createAttribute(entry.attributes)
        val id = getProductEntryPort.getNextSeriesId()
        val product = Product(
            id = id,
            nid = UUID.randomUUID(),
            businessId = entry.businessId,
            taxonomy = entry.taxonomy,
            managementMethodology = entry.managementMethodology,
            name = entry.name,
            brandId = entry.brandId ?: 0,
            industryId = entry.industryId ?: 0,
            categoryIds = setOf(),
            galleries = galleries,
            attributes = attributes,
            createdAt = Clock.System.now(),
            updatedAt = Clock.System.now(),
        )
        return storage(product)
    }

    private fun createGalleries(entries: List<GalleryEntry>?): List<Gallery> {
        if (entries.isNullOrEmpty()) return listOf()
        val ids = getGalleryUseCase.getNextSeriesIds(entries.size)
        val galleries = listOf<Gallery>()
        entries.forEachIndexed { index, galleryEntry ->
            galleries.toMutableList().add(
                Gallery(
                    id = ids.toMutableList()[index],
                    nid = UUID.randomUUID(),
                    storeId = galleryEntry.storeId,
                    default = galleryEntry.default,
                    position = galleryEntry.position,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            )
        }
        return galleries
    }

    private fun createAttribute(entries: List<ProductAttributeEntry>?): List<ProductAttribute> {
        if (entries.isNullOrEmpty()) return listOf()
        val ids = getProductAttributeUseCase.getNextSeriesIds(entries.size)
        val galleries = listOf<ProductAttribute>()
        entries.forEachIndexed { index, attributeEntry ->
            galleries.toMutableList().add(
                ProductAttribute(
                    id = ids.toMutableList()[index],
                    nid = UUID.randomUUID(),
                    name = attributeEntry.name,
                    values = attributeEntry.values,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            )
        }
        return galleries
    }

    private suspend fun storage(product: Product) = transactionService.transaction {
        return@transaction createProductEntryPort.insert(product)
    }
}