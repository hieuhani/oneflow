package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.IndustryEntity
import vn.periscope.adapters.persistence.repository.IndustryRepository
import vn.periscope.core.domain.Industry
import vn.periscope.ports.models.IndustryEntry
import vn.periscope.ports.out.CreateIndustryEntryPoint
import vn.periscope.ports.out.GetIndustryEntryPort
import vn.periscope.ports.out.UpdateIndustryEntryPort

class IndustryPersistence(
    private val industryRepository: IndustryRepository
) : CreateIndustryEntryPoint, GetIndustryEntryPort, UpdateIndustryEntryPort {

    override fun create(businessId: Long, industry: Industry): Industry {
        val entity = IndustryEntity(
            businessId = businessId,
            name = industry.name,
            machineName = industry.machineName,
            createdAt = industry.createdAt,
            updatedAt = industry.updatedAt
        )
        return industryRepository.insert(entity).toIndustry()
    }

    override fun findById(businessId: Long, id: Long): Industry {
        return industryRepository.findById(businessId, id).toIndustry()
    }

    override fun update(businessId: Long, id: Long, entry: IndustryEntry): Industry {
        TODO("Not yet implemented")
    }
}