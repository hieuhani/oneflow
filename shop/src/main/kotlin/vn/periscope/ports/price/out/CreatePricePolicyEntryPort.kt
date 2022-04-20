package vn.periscope.ports.price.out

import vn.periscope.ports.price.models.PriceListEntry

interface CreatePricePolicyEntryPort {
    fun create(priceList: PriceListEntry): PriceListEntry
}