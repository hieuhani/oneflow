package vn.periscope.ports.price.out

import vn.periscope.ports.price.models.PriceListEntry

interface GetPricePolicyEntryPort {
    fun findById(id: Long): PriceListEntry
}