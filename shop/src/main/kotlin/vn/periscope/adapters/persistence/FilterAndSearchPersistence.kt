package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.AttributeEntity
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.repository.*
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Gallery
import vn.periscope.core.domain.Product
import vn.periscope.ports.out.FilterAndSearchProductEntryPort
import vn.periscope.share.statics.AttributeReferType
import vn.periscope.share.statics.GalleryReferType
import java.util.stream.Collectors
import kotlin.streams.toList


class FilterAndSearchPersistence(
    private val productRepository: ProductRepository,
    private val galleryRepository: GalleryRepository,
    private val attributeRepository: AttributeRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val attributeValueRepository: AttributeValueRepository

) : FilterAndSearchProductEntryPort {
    override fun filter(businessId: Long): List<Product> {
        val entities = productRepository.filter(businessId)
        return toProducts(entities)
    }

    private fun toProducts(entities: List<ProductEntity>): List<Product> {
        if (entities.isEmpty()) return listOf()
        val productIds = entities.stream().map { it.id }.toList()
        val galleriesMap = fetchGalleries(productIds)
        val attributesMap = fetchAttributes(productIds)
        val categoryIdsMap = fetchCategories(productIds)
        return entities.stream().map {
            toProduct(it, galleriesMap, attributesMap, categoryIdsMap)
        }.toList()
    }

    private fun toProduct(
        entity: ProductEntity,
        galleriesMap: Map<Long, List<Gallery>>,
        attributesMap: Map<Long, List<Attribute>>,
        categoryIdsMap: Map<Long, Set<Long>>
    ): Product {
        val galleries = galleriesMap[entity.id]
        val attributes = attributesMap[entity.id]
        val categoryIds = categoryIdsMap[entity.id]
        return Product(
            id = entity.id,
            taxonomy = entity.taxonomy,
            type = entity.type,
            name = entity.name,
            brandId = entity.brandId,
            categoryIds = categoryIds ?: setOf(),
            galleries = galleries ?: listOf(),
            attributes = attributes ?: listOf(),
            industryId = entity.industryId,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }

    private fun fetchGalleries(productIds: List<Long>): Map<Long, List<Gallery>> {
        val entities = galleryRepository.findByReferIn(GalleryReferType.PRODUCT, productIds)
        val entitiesMap = entities.groupBy({ it.referId }, { it })
        return entitiesMap.entries.stream()
            .collect(Collectors.toMap(
                { e -> e.key },
                { e ->
                    e.value.stream().map {
                        Gallery(
                            it.id, it.storeId, it.position, it.createdAt, it.updatedAt
                        )
                    }.toList()
                }
            ))
    }

    private fun fetchAttributes(productIds: List<Long>): Map<Long, List<Attribute>> {
        val attributeEntities = attributeRepository.findByReferIn(AttributeReferType.PRODUCT, productIds)
        val attributeIds = attributeEntities.stream().map { it.id }.toList()

        val valueEntities = attributeValueRepository.findByAttributeIdIn(attributeIds)
        val valueMap = valueEntities.groupBy({ it.attributeId }, { it.value })

        val attributeEntitiesMap = attributeEntities.groupBy({ it.referId }, { it })

        return attributeEntitiesMap.entries.stream()
            .collect(Collectors.toMap(
                { e -> e.key },
                { e -> toAttribute(e.value, valueMap) }
            ))
    }

    private fun toAttribute(entities: List<AttributeEntity>, valueMap: Map<Long, List<String>>): List<Attribute> {
        val attributes = mutableListOf<Attribute>()
        for (entity in entities) {
            val values = valueMap[entity.id]
            if (values.isNullOrEmpty()) {
                continue
            }
            val attribute = Attribute(
                entity.id,
                entity.name,
                values.toSet(),
                entity.createdAt,
                entity.updatedAt
            )
            attributes.add(attribute)
        }
        return attributes
    }

    private fun fetchCategories(productIds: List<Long>): Map<Long, Set<Long>> {
        val entities = productCategoryRepository.findByProductIdIn(productIds)
        val entitiesMap = entities.groupBy({ it.productId }, { it.categoryId })
        return entitiesMap.entries.stream().collect(Collectors.toMap(
            { e -> e.key },
            { e -> e.value.toSet() }
        ))
    }
}