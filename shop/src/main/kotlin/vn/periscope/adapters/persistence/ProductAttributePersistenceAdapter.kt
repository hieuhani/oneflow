package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.ProductAttributeIdSequence
import vn.periscope.adapters.persistence.repository.IdProviderRepository
import vn.periscope.adapters.persistence.repository.ProductAttributeRepository
import vn.periscope.core.domain.ProductAttribute
import vn.periscope.ports.out.GetProductAttributeEntryPoint

class ProductAttributePersistenceAdapter(
    private val productAttributeRepository: ProductAttributeRepository,
    private val idProviderRepository: IdProviderRepository,
) : GetProductAttributeEntryPoint {


    override fun findById(id: Long): ProductAttribute? {
        return null
    }

    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return idProviderRepository.getNextSeriesIds(ProductAttributeIdSequence.sequence, quantity)
    }

}