package vn.periscope.adapters.persistence

import kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.*
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Product
import vn.periscope.ports.out.*
import vn.periscope.share.statics.GalleryTargetObjectType
import kotlin.streams.toList

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
        insertBatchCategory(product)
        insertBatchGallery(product)
        insertBatchAttribute(product)
    }

    private fun insertBatchGallery(product: Product) {
        if (product.galleries.isNullOrEmpty()) return
        val entities = product.galleries.stream().map {
            GalleryEntity(
                it.id,
                it.nid,
                product.businessId,
                GalleryTargetObjectType.PRODUCT,
                product.id,
                it.storeId,
                it.position,
                it.createdAt,
                it.createdAt
            )
        }.toList()
        galleryRepository.batchInsert(entities)
    }

    private fun insertBatchAttribute(product: Product) {
        if (product.attributes.isNullOrEmpty()) return
        val entities = product.attributes.stream().map {
            ProductAttributeEntity(
                it.id,
                it.nid,
                product.businessId,
                product.id,
                it.name,
                it.values,
                it.createdAt,
                it.createdAt
            )
        }.toList()
        attributeRepository.batchInsert(entities)
    }

    private fun insertBatchCategory(product: Product) {
        if (product.categoryIds.isNullOrEmpty()) return
        val entities = product.categoryIds.stream().map {
            ProductCategoryEntity(
                product.businessId,
                product.id,
                it,
                Clock.System.now()
            )
        }.toList()
        productCategoryRepository.batchInsert(entities)
    }

    override fun update(id: Long, product: Product) {
        return
    }

    override fun delete(id: Long): Boolean {
        return productRepository.delete(id)
    }
}