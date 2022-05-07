package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.AttributeEntity
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.repository.AttributeRepository
import vn.periscope.adapters.persistence.repository.AttributeValueRepository
import vn.periscope.adapters.persistence.repository.ProductCategoryRepository
import vn.periscope.adapters.persistence.repository.ProductRepository
import vn.periscope.core.domain.Attribute
import vn.periscope.core.domain.Product
import vn.periscope.ports.out.FindProductEntryPort
import vn.periscope.share.statics.AttributeReferType
import java.util.stream.Collectors
import kotlin.streams.toList


class FindPersistence(
    private val productRepository: ProductRepository,
    private val attributeRepository: AttributeRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val attributeValueRepository: AttributeValueRepository

) : FindProductEntryPort {
    override fun find(businessId: Long): List<Product> {
        val entities = productRepository.filter(businessId)
        return toProducts(entities)
    }

    private fun toProducts(entities: List<ProductEntity>): List<Product> {
        if (entities.isEmpty()) return listOf()
        val productIds = entities.stream().map { it.id }.toList()
        val attributesMap = fetchAttributes(productIds)
        val categoryIdsMap = fetchCategories(productIds)
        return entities.stream().map {
            toProduct(it, attributesMap, categoryIdsMap)
        }.toList()
    }

    private fun toProduct(
        entity: ProductEntity,
        attributesMap: Map<Long, List<Attribute>>,
        categoryIdsMap: Map<Long, Set<Long>>
    ): Product {
        val attributes = attributesMap[entity.id]
        val categoryIds = categoryIdsMap[entity.id]
        return Product(
            id = entity.id,
            type = entity.type,
            name = entity.name,
            brandId = entity.brandId,
            categoryIds = categoryIds ?: setOf(),
            photoId = entity.photoId,
            attributes = attributes ?: listOf(),
            industryId = entity.industryId,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
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