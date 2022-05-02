package vn.periscope.ports

interface GetAttributeUseCase {
    fun getNextSeriesIds(quantity: Int): Set<Long>
}