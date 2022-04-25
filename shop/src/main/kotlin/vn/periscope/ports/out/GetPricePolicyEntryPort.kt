package vn.periscope.ports.out

import vn.periscope.ports.models.PriceListEntry

interface GetPricePolicyEntryPort {
    fun findById(id: Long): PriceListEntry
}