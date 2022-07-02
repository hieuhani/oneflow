package vn.periscope.ports.out

interface GetAllPricePolicyEntryPort {
    fun findAll(): List<PriceListEntry>
}