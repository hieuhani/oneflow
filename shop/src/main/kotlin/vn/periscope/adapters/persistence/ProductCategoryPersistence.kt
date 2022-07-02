package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.repository.ProductCategoryRepository
import vn.periscope.ports.out.CreateProductCategoryEntryPort

class ProductCategoryPersistence(
    private val productCategoryRepository: ProductCategoryRepository,
) : CreateProductCategoryEntryPort {
    override fun create(productId: Long, categoryId: Long) {
        productCategoryRepository.insert(productId, categoryId)
    }
}