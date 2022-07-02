package vn.periscope.ports.out

interface UpdatePricePolicyEntryPort {
    fun update(id: Long, priceList: PriceListEntry): PriceListEntry
}