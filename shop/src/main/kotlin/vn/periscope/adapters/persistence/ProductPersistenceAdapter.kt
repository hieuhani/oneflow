package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.entity.ProductIdSequence
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Product
import vn.periscope.ports.out.*

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val galleryRepository: GalleryRepository,
    private val attributeRepository: ProductAttributeRepository,
    private val idProviderRepository: IdProviderRepository,
    private val productCategoryRepository: ProductCategoryRepository
) : GetProductEntryPort,
    CreateProductEntryPort,
    UpdateProductEntryPort,
    DeleteProductEntryPort,
    FilterProductEntryPort,
    SearchProductEntryPort {
    override fun filter(): List<Product> {
        return listOf()
    }

    override fun search(): List<Product> {
        return listOf()
    }

    override fun findById(id: Long): Product? {
        return null
    }

    override fun getNextSeriesId(): Long {
        return idProviderRepository.getNextSeriesId(ProductIdSequence.sequence)
    }

    override fun insert(product: Product) {
        productRepository.insert(
            ProductEntity(
                id = product.id,
                nid = product.nid,
                businessId = product.businessId,
                taxonomy = product.taxonomy,
                managementMethodology = product.managementMethodology,
                name = product.name,
                brandId = product.brandId.let { 0 },
                industryId = product.industryId.let { 0 },
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
            )
        )
        insertBatchGallery(product)
    }

    private fun insertBatchGallery(product: Product) {
        if (product.galleries.isNullOrEmpty()) return

    }

    override fun update(id: Long, product: Product) {
        return
    }

    override fun delete(id: Long): Boolean {
        return productRepository.delete(id)
    }
}