package vn.periscope.adapters.persistence

import kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.*
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Product
import vn.periscope.ports.out.CreateProductEntryPort
import vn.periscope.share.statics.AttributeReferType
import vn.periscope.share.statics.GalleryReferType
import java.util.*
import kotlin.streams.toList

class CreateProductPersistence(
    private val productRepository: ProductRepository,
    private val galleryRepository: GalleryRepository,
    private val attributeRepository: AttributeRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val attributeValueRepository: AttributeValueRepository,
) : CreateProductEntryPort {

    override fun insert(businessId: Long, product: Product) {
        productRepository.insert(
            ProductEntity(
                id = product.id,
                businessId = businessId,
                taxonomy = product.taxonomy,
                type = product.type,
                name = product.name,
                brandId = product.brandId,
                industryId = product.industryId,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
                nid = UUID.randomUUID(),
            )
        )
        insertBatchCategory(businessId, product)
        insertBatchGallery(businessId, product)
        insertBatchAttribute(businessId, product)
    }

    private fun insertBatchGallery(businessId: Long, product: Product) {
        if (product.galleries.isEmpty()) return
        val entities = product.galleries.stream().map {
            GalleryEntity(
                it.id,
                businessId,
                GalleryReferType.PRODUCT,
                product.id,
                it.storeId,
                it.position,
                it.createdAt,
                it.createdAt,
                UUID.randomUUID()
            )
        }.toList()
        galleryRepository.batchInsert(entities)
    }

    private fun insertBatchAttribute(businessId: Long, product: Product) {
        if (product.attributes.isEmpty()) return
        val entities = product.attributes.stream().map {
            AttributeEntity(
                it.id,
                businessId,
                AttributeReferType.PRODUCT,
                product.id,
                it.name,
                it.createdAt,
                it.updatedAt,
                UUID.randomUUID()
            )
        }.toList()
        attributeRepository.batchInsert(entities)
        insertBatchAttributeValue(product.attributes)
    }

    private fun insertBatchAttributeValue(attributes: List<Attribute>) {
        val entities = mutableListOf<AttributeValueEntity>()
        for (attribute in attributes) {
            entities.addAll(
                attribute.values.stream().map {
                    AttributeValueEntity(
                        attribute.id,
                        it
                    )
                }.toList()
            )
        }
        attributeValueRepository.batchInsert(entities)
    }

    private fun insertBatchCategory(businessId: Long, product: Product) {
        if (product.categoryIds.isEmpty()) return
        val entities = product.categoryIds.stream().map {
            ProductCategoryEntity(
                businessId,
                product.id,
                it,
                Clock.System.now()
            )
        }.toList()
        productCategoryRepository.batchInsert(entities)
    }
}