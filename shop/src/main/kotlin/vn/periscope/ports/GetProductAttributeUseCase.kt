package vn.periscope.ports

interface GetProductAttributeUseCase {
    fun getNextSeriesIds(quantity: Int): Set<Long>
}