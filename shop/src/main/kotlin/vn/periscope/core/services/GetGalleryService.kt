package vn.periscope.core.services

import vn.periscope.ports.GetGalleryUseCase
import vn.periscope.ports.out.GetGalleryEntryPort

class GetGalleryService(
    private val getGalleryEntryPort: GetGalleryEntryPort
) : GetGalleryUseCase {
    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return getGalleryEntryPort.getNextSeriesIds(quantity)
    }

}