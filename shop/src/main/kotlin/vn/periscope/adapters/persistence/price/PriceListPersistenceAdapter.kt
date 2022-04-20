package vn.periscope.adapters.persistence.price

import vn.periscope.adapters.persistence.price.entity.toEntry
import vn.periscope.adapters.persistence.price.repository.PriceListRepository
import vn.periscope.ports.price.models.PriceListEntry
import vn.periscope.ports.price.out.*

class PriceListPersistenceAdapter(
    private val priceListRepository: PriceListRepository,
) : GetPricePolicyEntryPort, GetAllPricePolicyEntryPort, CreatePricePolicyEntryPort, UpdatePricePolicyEntryPort,
    DeletePricePolicyEntryPort {

    override fun findById(id: Long): PriceListEntry {
        return priceListRepository.get(id).toEntry()
    }

    override fun findAll(): List<PriceListEntry> {
        return priceListRepository.getAll().map { it.toEntry() }
    }

    override fun create(priceList: PriceListEntry): PriceListEntry {
        return priceListRepository.create(priceList).toEntry()
    }

    override fun update(id: Long, priceList: PriceListEntry): PriceListEntry {
        return priceListRepository.update(id, priceList).toEntry()
    }

    override fun delete(id: Long): Boolean {
        return priceListRepository.delete(id)
    }
}