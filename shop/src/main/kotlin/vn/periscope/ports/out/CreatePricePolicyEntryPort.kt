package vn.periscope.ports.out

interface CreatePricePolicyEntryPort {
    fun create(priceList: PriceListEntry): PriceListEntry
}