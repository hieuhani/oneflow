package vn.periscope.adapters.persistence

import kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.*
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.core.domain.ProductAttribute
import vn.periscope.ports.out.*
import vn.periscope.share.statics.GalleryTargetObjectType
import kotlin.streams.toList

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val galleryRepository: GalleryRepository,
    private val attributeRepository: ProductAttributeRepository,
    private val idProviderRepository: IdProviderRepository,
    private val productCategoryRepository: ProductCategoryRepository
) : GetProductEntryPort, CreateProductEntryPort, UpdateProductEntryPort, DeleteProductEntryPort, FilterAndSearchProductEntryPort{
    override fun filter(): List<Product> {
        return listOf()
    }

    override fun findById(id: Long): Product {
        val entity = productRepository.findById(id)
        return toProduct(entity)
    }

    override fun getNextSeriesId(): Long {
        return idProviderRepository.getNextSeriesId(ProductIdSequence.sequence)
    }

    override fun findByIdAndBusinessId(id: Long, businessId: Long): Product {
        val entity = productRepository.findByIdAndBusinessId(id, businessId)
        return toProduct(entity)
    }

    private fun toProduct(entity: ProductEntity): Product {
        val product = Product(
            id = entity.id,
            nid = entity.nid,
            businessId = entity.businessId,
            taxonomy = entity.taxonomy,
            managementMethodology = entity.managementMethodology,
            name = entity.name,
            brandId = entity.brandId,
            categoryIds = setOf(),
            galleries = listOf(),
            attributes = listOf(),
            industryId = entity.industryId,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
        fetchGalleriesToProduct(product)
        fetchAttributesToProduct(product)
        fetchCategoriesToProduct(product)
        return product
    }

    private fun fetchGalleriesToProduct(product: Product) {
        val entities =
            galleryRepository.mustFilter(
                targetObjectType = GalleryTargetObjectType.PRODUCT,
                targetObjectIds = listOf(product.id),
            )
        val galleries = entities.stream().map {
            Gallery(
                it.id, it.nid, it.storeId, it.position, it.createdAt, it.createdAt
            )
        }.toList()
        product.galleries?.toMutableList()?.addAll(galleries)
    }

    private fun fetchAttributesToProduct(product: Product) {
        val entities = attributeRepository.mustFilter(
            productIds = listOf(product.id)
        )
        val attributes = entities.stream().map {
            ProductAttribute(
                it.id, it.nid, it.name, it.values, it.createdAt, it.createdAt
            )
        }.toList()
        product.attributes?.toMutableList()?.addAll(attributes)
    }

    private fun fetchCategoriesToProduct(product: Product) {
        val entities = productCategoryRepository.findByProductId(product.id)
        val categoryIds = entities.stream().map { it.categoryId }.toList()
        product.categoryIds?.toMutableList()?.addAll(categoryIds)
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
                brandId = product.brandId ?: 0,
                industryId = product.industryId ?: 0,
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
                it.id, it.nid, product.businessId, product.id, it.name, it.values, it.createdAt, it.createdAt
            )
        }.toList()
        attributeRepository.batchInsert(entities)
    }

    private fun insertBatchCategory(product: Product) {
        if (product.categoryIds.isNullOrEmpty()) return
        val entities = product.categoryIds.stream().map {
            ProductCategoryEntity(
                product.businessId, product.id, it, Clock.System.now()
            )
        }.toList()
        productCategoryRepository.batchInsert(entities)
    }

    override fun update(id: Long, product: Product) {
        return
    }

    override fun delete(id: Long, businessId: Long): Boolean {
       return productRepository.delete(id, businessId)
    }
}