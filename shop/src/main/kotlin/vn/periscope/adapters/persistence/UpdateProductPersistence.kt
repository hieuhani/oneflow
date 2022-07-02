package vn.periscope.adapters.persistence

import kotlinx.datetime.Instant
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.repository.ProductCategoryRepository
import vn.periscope.adapters.persistence.repository.ProductRepository
import vn.periscope.core.domain.Product
import vn.periscope.ports.models.ProductEntry
import vn.periscope.ports.out.UpdateProductEntryPort
import java.util.stream.Collectors.toSet

class UpdateProductPersistence(
    private val productRepository: ProductRepository,
    private val productCategoryRepository: ProductCategoryRepository,
) : UpdateProductEntryPort {

    override fun update(businessId: Long, id: Long, entry: ProductEntry): Product {
        val entity = productRepository.update(
            businessId,
            id,
            entry
        )
        updateBatchCategory(id, entry.categoryColection)
        return toProduct(entity, entry.categoryColection)
    }

    private fun updateBatchCategory(productId: Long, categoryIds: Set<Long>) {
        if (categoryIds.isEmpty()) {
            productCategoryRepository.batchDelete(productId, categoryIds)
        }
        val currentIds = productCategoryRepository.findByProductId(productId).stream().map { it.categoryId }.collect(toSet())
        val deleteIds = currentIds.stream().filter { !categoryIds.contains(it) }.collect(toSet())
        val createIds = categoryIds.stream().filter { !currentIds.contains(it) }.collect(toSet())
        productCategoryRepository.batchDelete(productId, deleteIds)
        productCategoryRepository.batchInsert(productId, createIds)
    }

    private fun toProduct(entity: ProductEntity, categoryIds: Set<Long>) = Product(
        id = entity.id.value,
        type = entity.type,
        name = entity.name,
        photoId = entity.photoId,
        brandId = entity.brandId,
        industryId = entity.industryId,
        price = entity.price,
        categoryIds = categoryIds,
        createdAt = Instant.fromEpochMilliseconds(entity.createdAt.toEpochMilli()),
        updatedAt = Instant.fromEpochMilliseconds(entity.updatedAt.toEpochMilli())
    )
}