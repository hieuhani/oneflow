package vn.periscope.adapters.persistence

import kotlinx.datetime.Instant
import vn.periscope.adapters.persistence.entity.IndustryEntity
import vn.periscope.adapters.persistence.repository.IndustryRepository
import vn.periscope.core.domain.Industry
import vn.periscope.ports.models.IndustryEntry
import vn.periscope.ports.out.*
import kotlin.streams.toList

class IndustryPersistence(
    private val industryRepository: IndustryRepository
) : CreateIndustryEntryPort, GetIndustryEntryPort, UpdateIndustryEntryPort, FindIndustryEntryPort,
    DeleteIndustryEntryPort {

    override fun create(businessId: Long, entry: IndustryEntry): Industry {
        val entity = industryRepository.insert(businessId, entry)
        return toIndustry(entity)
    }

    override fun findById(businessId: Long, id: Long): Industry {
        val entity = industryRepository.getById(businessId, id)
            ?: throw Error("Update industry failure, not found industry id=$id")
        return toIndustry(entity)
    }

    override fun update(businessId: Long, id: Long, entry: IndustryEntry): Industry {
        val entity = industryRepository.update(businessId, id, entry)
        return toIndustry(entity)
    }

    private fun toIndustry(entity: IndustryEntity) = Industry(
        id = entity.id.value,
        name = entity.name,
        machineName = entity.machineName,
        createdAt = Instant.fromEpochMilliseconds(entity.createdAt.toEpochMilli()),
        updatedAt = Instant.fromEpochMilliseconds(entity.updatedAt.toEpochMilli())
    )

    override fun find(businessId: Long): List<Industry> {
        val entities = industryRepository.find(businessId)
        return entities.stream().map { toIndustry(it) }.toList()
    }

    override fun delete(businessId: Long, id: Long) {
        industryRepository.delete(businessId, id)
    }
}