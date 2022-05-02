package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.AttributeIdSequence
import vn.periscope.adapters.persistence.repository.IdProviderRepository
import vn.periscope.adapters.persistence.repository.AttributeRepository
import vn.periscope.core.domain.Attribute
import vn.periscope.ports.out.GetAttributeEntryPoint

class GetAttributePersistence(
    private val productAttributeRepository: AttributeRepository,
    private val idProviderRepository: IdProviderRepository,
) : GetAttributeEntryPoint {


    override fun findById(id: Long): Attribute? {
        return null
    }

    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return idProviderRepository.getNextSeriesIds(AttributeIdSequence.sequence, quantity)
    }

}