package vn.periscope.ports.out

import vn.periscope.ports.models.PriceListEntry

interface GetAllPricePolicyEntryPort {
    fun findAll(): List<PriceListEntry>
}