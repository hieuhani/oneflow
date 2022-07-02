package vn.periscope.adapters.persistence

import kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.entity.ProductIdSequence
import vn.periscope.adapters.persistence.repository.AttributeRepository
import vn.periscope.adapters.persistence.repository.IdProviderRepository
import vn.periscope.adapters.persistence.repository.ProductRepository
import vn.periscope.core.domain.Product
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.*

class ProductPersistence(
    private val productRepository: ProductRepository,
    private val attributeRepository: AttributeRepository
) : CreateProductEntryPort {

    override fun create(businessId: Long, entry: ProductEntry): ProductEntry {
        val entity = productRepository.insert(
            ProductEntity(
                businessId = businessId,
                type = entry.type,
                name = entry.name.value,
                photoId = entry.photo.value,
                brandId = entry.brand,
                industryId = entry.industry,
                price = entry.price,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            )
        ) ?: throw Error("Create product failure")
        return entity.toEntry()
    }
}