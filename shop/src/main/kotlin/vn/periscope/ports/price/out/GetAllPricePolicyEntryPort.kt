package vn.periscope.ports.price.out

import vn.periscope.ports.price.models.PriceListEntry

interface GetAllPricePolicyEntryPort {
    fun findAll(): List<PriceListEntry>
}