package vn.periscope.ports.out

import vn.periscope.core.domain.Gallery

interface GetGalleryEntryPort {
    fun findById(id: Long): Gallery?
    fun getNextSeriesIds(quantity: Int): Set<Long>
}