package vn.periscope.ports.price.out

import vn.periscope.ports.price.models.PriceListEntry

interface UpdatePricePolicyEntryPort {
    fun update(id: Long, priceList: PriceListEntry): PriceListEntry
}