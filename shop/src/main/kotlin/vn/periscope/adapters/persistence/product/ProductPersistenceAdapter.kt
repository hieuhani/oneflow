package vn.periscope.adapters.persistence.product

import vn.periscope.ports.product.*
import vn.periscope.ports.product.models.Product

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
) : GetProductEntryPort,
    CreateProductEntryPort,
    UpdateProductEntryPort,
    DeleteProductEntryPort,
    FilterProductEntryPort,
    SearchProductEntryPort {
    override fun filter(): List<Product> {
        return productRepository.getAll().map { it.toEntry() }
    }

    override fun search(): List<Product> {
        return productRepository.getAll().map { it.toEntry() }
    }

    override fun findById(id: Long): Product {
        return productRepository.get(id).toEntry()
    }

    override fun create(product: Product): Product {
        return productRepository.create(product).toEntry()
    }

    override fun update(id: Long, product: Product): Product {
        return productRepository.update(id, product).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return productRepository.delete(id)
    }
}