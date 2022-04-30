package vn.periscope.adapters.persistence

import kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.*
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.core.domain.Attribute
import vn.periscope.ports.out.*
import vn.periscope.share.statics.ObjectReferenceType
import java.util.*
import kotlin.streams.toList

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val galleryRepository: GalleryRepository,
    private val attributeRepository: AttributeRepository,
    private val idProviderRepository: IdProviderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val attributeValueRepository: AttributeValueRepository,
) : GetProductEntryPort, CreateProductEntryPort, UpdateProductEntryPort, DeleteProductEntryPort,
    FilterAndSearchProductEntryPort {
    override fun filter(): List<Product> {
        return listOf()
    }

    override fun getNextSeriesId(): Long {
        return idProviderRepository.getNextSeriesId(ProductIdSequence.sequence)
    }

    override fun findById(id: Long, businessId: Long): Product {
        val entity = productRepository.findById(id, businessId)
        return toProduct(entity)
    }

    private fun toProduct(entity: ProductEntity): Product {
        val product = Product(
            id = entity.id,
            taxonomy = entity.taxonomy,
            type = entity.type,
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
        val entities = galleryRepository.findByReference(ObjectReferenceType.PRODUCT, product.id)
        val galleries = entities.stream().map {
            Gallery(
                it.id, , it.storeId, it.position, it.createdAt, it.createdAt
            )
        }.toList()
        product.galleries?.toMutableList()?.addAll(galleries)
    }

    private fun fetchAttributesToProduct(product: Product) {
        val attributeEntities = attributeRepository.findByRefer(ObjectReferenceType.PRODUCT, product.id)
        if (attributeEntities.isEmpty()) return

        val attributeEntityIds = attributeEntities.stream().map { it.id }.toList()
        val attributeValueEntities = attributeValueRepository.findByAttributeIds(attributeEntityIds.toSet())

        val attributeValueMap = attributeValueEntities.groupBy({ it.attributeNID }, { it.value })
        val attributes = attributeEntities.stream().map {
            {
                Attribute(
                    it.id,
                    it.name,
                    ,
                    it.createdAt,
                    it.createdAt
                )
            }

        }.toList()
        product.attributes?.toMutableList()?.addAll(attributes)
    }

    private fun fetchCategoriesToProduct(product: Product) {
        val entities = productCategoryRepository.findByProductId(product.id)
        val categoryIds = entities.stream().map { it.categoryId }.toList()
        product.categoryIds?.toMutableList()?.addAll(categoryIds)
    }

    override fun insert(businessId: Long, product: Product) {
        productRepository.insert(
            ProductEntity(
                id = product.id,
                businessId = businessId,
                taxonomy = product.taxonomy,
                type = product.type,
                name = product.name,
                brandId = product.brandId ?: 0,
                industryId = product.industryId ?: 0,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                nid = UUID.randomUUID(),
            )
        )
        insertBatchCategory(businessId, product.id, product.categoryIds)
        insertBatchGallery(businessId, product.id, product.galleries)
        insertBatchAttribute(businessId, product)
    }

    private fun insertBatchGallery(businessId: Long, productId: Long, galleries: List<Gallery>?) {
        if (galleries.isNullOrEmpty()) return
        val entities = galleries.stream().map {
            GalleryEntity(
                it.id,
                businessId,
                ObjectReferenceType.PRODUCT,
                productId,
                it.storeId,
                it.position,
                it.createdAt,
                it.createdAt,
                UUID.randomUUID()
            )
        }.toList()
        galleryRepository.batchInsert(entities)
    }

    private fun insertBatchAttribute(businessId: Long, productNID: UUID, attributes: List<Attribute>?) {
        if (attributes.isNullOrEmpty()) return
        for (attribute in attributes) {
            val nid = UUID.randomUUID()
            val attributeEntity = AttributeEntity(
                attribute.id,
                businessId,
                productNID,
                attribute.name,
                attribute.createdAt,
                attribute.updatedAt,
                nid
            )
            attributeRepository.insert(attributeEntity)

            val attributeValueEntities = attribute.values.stream().map {
                AttributeValueEntity(
                    attributeNID = nid,
                    value = it
                )
            }.toList()
            attributeValueRepository.batchInsert(attributeValueEntities)
        }
    }

    private fun insertBatchCategory(businessId: Long, productId: Long, categoryIds: Set<Long>?) {
        if (categoryIds.isNullOrEmpty()) return
        val entities = categoryIds.stream().map {
            ProductCategoryEntity(
                businessId,
                productId,
                it,
                Clock.System.now()
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