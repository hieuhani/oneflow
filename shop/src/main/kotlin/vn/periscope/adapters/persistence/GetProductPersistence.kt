package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.entity.ProductIdSequence
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.ports.out.GetProductEntryPort
import vn.periscope.share.statics.AttributeReferType
import vn.periscope.share.statics.GalleryReferType
import kotlin.streams.toList

class GetProductPersistence(
    private val productRepository: ProductRepository,
    private val galleryRepository: GalleryRepository,
    private val attributeRepository: AttributeRepository,
    private val idProviderRepository: IdProviderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val attributeValueRepository: AttributeValueRepository,
) : GetProductEntryPort {
    override fun getNextSeriesId(): Long {
        return idProviderRepository.getNextSeriesId(ProductIdSequence.sequence)
    }

    override fun findById(id: Long, businessId: Long): Product {
        val entity = productRepository.findById(id, businessId)
        return toProduct(entity)
    }

    private fun toProduct(entity: ProductEntity): Product {
        val galleries = fetchGalleries(entity.id)
        val attributes = fetchAttributes(entity.id)
        val categoryIds = fetchCategories(entity.id)
        return Product(
            id = entity.id,
            taxonomy = entity.taxonomy,
            type = entity.type,
            name = entity.name,
            brandId = entity.brandId,
            categoryIds = categoryIds,
            galleries = galleries,
            attributes = attributes,
            industryId = entity.industryId,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }

    private fun fetchGalleries(productId: Long): List<Gallery> {
        val entities = galleryRepository.findByRefer(GalleryReferType.PRODUCT, productId)
        return entities.stream().map {
            Gallery(
                it.id, it.storeId, it.position, it.createdAt, it.updatedAt
            )
        }.toList()
    }

    private fun fetchAttributes(productId: Long): List<Attribute> {
        val attributeEntities = attributeRepository.findByRefer(AttributeReferType.PRODUCT, productId)
        val attributeIds = attributeEntities.stream().map { it.id }.toList()
        val valueEntities = attributeValueRepository.findByAttributeIdIn(attributeIds)
        val valueMap = valueEntities.groupBy({ it.attributeId }, { it.value })

        val attributes = mutableListOf<Attribute>()
        for (attributeEntity in attributeEntities) {
            val values = valueMap[attributeEntity.id]
            if (values.isNullOrEmpty()) {
                continue
            }
            val attribute = Attribute(
                attributeEntity.id,
                attributeEntity.name,
                values.toSet(),
                attributeEntity.createdAt,
                attributeEntity.updatedAt
            )
            attributes.add(attribute)
        }
        return attributes
    }

    private fun fetchCategories(productId: Long): Set<Long> {
        val entities = productCategoryRepository.findByProductId(productId)
        return entities.stream().map { it.categoryId }.toList().toSet()
    }
}