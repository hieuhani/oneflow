package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.repository.ProductRepository
import vn.periscope.core.domain.Product
import vn.periscope.ports.product.out.*

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
        return ProductRepository.findById(id).toEntry()
    }

    override fun create(product: Product): Product {
        return productRepository.create(productEntry).toEntry()
    }

    override fun update(id: Long, product: Product): Product {
        return productRepository.update(id, productEntry).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return productRepository.delete(id)
    }
}