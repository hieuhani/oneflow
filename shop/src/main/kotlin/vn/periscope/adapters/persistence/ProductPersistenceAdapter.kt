package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.repository.GalleryRepository
import vn.periscope.adapters.persistence.repository.ProductAttributeRepository
import vn.periscope.adapters.persistence.repository.ProductRepository
import vn.periscope.core.domain.Product
import vn.periscope.ports.product.out.*

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val galleryRepository : GalleryRepository,
    private val attributeRepository: ProductAttributeRepository,
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
        return productRepository.findById(id) ?: throw RuntimeException("Product not exists")
    }

    override fun create(product: Product): Product {
        val product = productRepository.insert(product)
    }

    override fun update(id: Long, product: Product): Product {
        return productRepository.update(id, product)
    }

    override fun delete(id: Long): Boolean {
        return productRepository.delete(id)
    }
}