package vn.periscope.ports.out

import vn.periscope.ports.models.AttributeEntry
import vn.periscope.share.statics.AttributeReferType

interface GetAttributeEntryPort {
    fun getNextSeriesIds(quantity: Int): Set<Long>

    fun getByReferTypeAndReferId(
        businessId: Long,
        referType: AttributeReferType,
        referId: Long
    ): List<AttributeEntry>
}