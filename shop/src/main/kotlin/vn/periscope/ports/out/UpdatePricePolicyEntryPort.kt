package vn.periscope.ports.out

import vn.periscope.ports.models.PriceListEntry

interface UpdatePricePolicyEntryPort {
    fun update(id: Long, priceList: PriceListEntry): PriceListEntry
}