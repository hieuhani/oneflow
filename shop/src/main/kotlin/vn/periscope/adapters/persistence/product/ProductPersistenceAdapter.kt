package vn.periscope.adapters.persistence.product

import vn.periscope.ports.product.models.ProductEntry
import vn.periscope.ports.product.out.*

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
) : GetProductEntryPort,
    CreateProductEntryPort,
    UpdateProductEntryPort,
    DeleteProductEntryPort,
    FilterProductEntryPort,
    SearchProductEntryPort {
    override fun filter(): List<ProductEntry> {
        return productRepository.getAll().map { it.toEntry() }
    }

    override fun search(): List<ProductEntry> {
        return productRepository.getAll().map { it.toEntry() }
    }

    override fun findById(id: Long): ProductEntry {
        return productRepository.get(id).toEntry()
    }

    override fun create(productEntry: ProductEntry): ProductEntry {
        return productRepository.create(productEntry).toEntry()
    }

    override fun update(id: Long, productEntry: ProductEntry): ProductEntry {
        return productRepository.update(id, productEntry).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return productRepository.delete(id)
    }
}