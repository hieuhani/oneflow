package vn.periscope.ports.out

import vn.periscope.ports.models.PriceListEntry

interface CreatePricePolicyEntryPort {
    fun create(priceList: PriceListEntry): PriceListEntry
}