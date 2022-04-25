package vn.periscope.ports

interface GetGalleryUseCase {
    fun getNextSeriesIds(quantity: Int): Set<Long>
}