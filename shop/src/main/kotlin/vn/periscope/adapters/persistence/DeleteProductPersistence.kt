package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.repository.ProductRepository
import vn.periscope.ports.out.DeleteProductEntryPort

class DeleteProductPersistence(
    private val productRepository: ProductRepository,
) : DeleteProductEntryPort {
    override fun delete(id: Long, businessId: Long) {
        productRepository.delete(id, businessId)
    }
}