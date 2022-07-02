package vn.periscope.adapters.persistence

import kotlinx.datetime.Clock
import vn.periscope.adapters.persistence.entity.AttributeEntity
import vn.periscope.adapters.persistence.entity.AttributeIdSequence
import vn.periscope.adapters.persistence.repository.AttributeRepository
import vn.periscope.adapters.persistence.repository.AttributeValueRepository
import vn.periscope.adapters.persistence.repository.IdProviderRepository
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.out.CreateAttributeEntryPort
import vn.periscope.ports.out.DeleteAttributeEntryPort
import vn.periscope.ports.out.GetAttributeEntryPort
import vn.periscope.ports.out.UpdateAttributeEntryPort
import vn.periscope.share.statics.AttributeReferType
import java.util.stream.Collectors
import kotlin.streams.toList

class AttributePersistence(
    private val attributeRepository: AttributeRepository,
    private val attributeValueRepository: AttributeValueRepository,
    private val idProviderRepository: IdProviderRepository,
) : CreateAttributeEntryPort, GetAttributeEntryPort, DeleteAttributeEntryPort, UpdateAttributeEntryPort {

    private fun insertBatchAttributeValue(attributeId: Long, values: Set<String>) {
        val entities = mutableListOf<AttributeValueEntity>()
        entities.addAll(
            values.stream().map {
                AttributeValueEntity(
                    attributeId,
                    it
                )
            }.toList()
        )
        attributeValueRepository.batchInsert(entities)
    }

    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return idProviderRepository.getNextSeriesIds(AttributeIdSequence.sequence, quantity)
    }

    override fun getByReferTypeAndReferId(
        businessId: Long,
        referType: AttributeReferType,
        referId: Long
    ): List<AttributeEntry> {
        val attributeEntities = attributeRepository.findByRefer(AttributeReferType.PRODUCT, referId)
        val attributeIds = attributeEntities.stream().map { it.id }.toList()

        val valueEntities = attributeValueRepository.findByAttributeIdIn(attributeIds)
        val valueMap = valueEntities.groupBy({ it.attributeId }, { it.value })

        val attributeEntitiesMap = attributeEntities.groupBy({ it.referId }, { it })

        return attributeEntitiesMap.entries.stream()
            .collect(
                Collectors.toMap(
                { e -> e.key },
                { e -> toAttribute(e.value, valueMap) }
            )).values
    }

    private fun toAttribute(entities: List<AttributeEntity>, valueMap: Map<Long, List<String>>): List<AttributeEntry> {
        val attributes = mutableListOf<AttributeEntry>()
        for (entity in entities) {
            val values = valueMap[entity.id]
            if (values.isNullOrEmpty()) {
                continue
            }
            val attribute = AttributeEntry(
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

    override fun create(
        businessId: Long,
        referType: AttributeReferType,
        referId: Long,
        entry: AttributeEntry
    ): AttributeEntry {
        val entity = attributeRepository.insert(
            AttributeEntity(
                entry.id,
                businessId,
                referType,
                referId,
                entry.name,
                Clock.System.now(),
                Clock.System.now(),
            )
        ) ?: throw Error("Create attribute failure")
        insertBatchAttributeValue(entity.id, entry.values)
        return toEntry(entity, entry.values)
    }

    override fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    fun toEntry(entity: AttributeEntity, values: Set<String>) = AttributeEntry(
        entity.id,
        entity.name,
        values,
        entity.createdAt,
        entity.updatedAt
    )
}
