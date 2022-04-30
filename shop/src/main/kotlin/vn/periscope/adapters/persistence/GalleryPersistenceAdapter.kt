package vn.periscope.adapters.persistence

import vn.periscope.adapters.persistence.entity.GalleryIdSequence
import vn.periscope.adapters.persistence.repository.GalleryRepository
import vn.periscope.adapters.persistence.repository.IdProviderRepository
import vn.periscope.core.domain.Gallery
import vn.periscope.ports.out.*

class GalleryPersistenceAdapter(
    private val galleryRepository: GalleryRepository,
    private val idProviderRepository: IdProviderRepository,
) : GetGalleryEntryPort{

    override fun findById(id: Long): Gallery? {
        return null
    }

    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return idProviderRepository.getNextSeriesIds(GalleryIdSequence.sequence, quantity)
    }

}