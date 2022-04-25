package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.core.domain.ProductAttribute
import vn.periscope.ports.TransactionService
import vn.periscope.ports.product.CreateProductUseCase
import vn.periscope.ports.product.models.ProductEntry
import vn.periscope.ports.product.out.CreateGalleryEntryPort
import vn.periscope.ports.product.out.CreateProductAttributeEntryPoint
import vn.periscope.ports.product.out.CreateProductEntryPort
import java.util.*
import kotlin.streams.toList

class CreateProductService(
    private val transactionService: TransactionService,
    private val createProductEntryPort: CreateProductEntryPort
) : CreateProductUseCase {

    override suspend fun create(entry: ProductEntry): Product {
        val galleries = entry.galleries.orEmpty().stream()
            .map {
                Gallery(
                    id = 0,
                    nid = UUID.randomUUID(),
                    storeId = it.storeId,
                    default = it.default,
                    position = it.position,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            }
            .toList()

        val attributes = entry.attributes.orEmpty().stream()
            .map {
                ProductAttribute(
                    id = entry.id,
                    nid = UUID.randomUUID(),
                    name = it.name,
                    values = it.values,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            }
            .toList()

        val product = Product(
            id = entry.id,
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

    private suspend fun storage(product: Product) = transactionService.transaction {
        return@transaction createProductEntryPort.create(product)
    }
}