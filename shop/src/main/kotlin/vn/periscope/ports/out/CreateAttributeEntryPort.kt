package vn.periscope.ports.out

import vn.periscope.ports.models.AttributeEntry
import vn.periscope.share.statics.AttributeReferType

interface CreateAttributeEntryPort {
    fun create(
        businessId: Long,
        referType: AttributeReferType,
        referId: Long,
        entry: AttributeEntry
    ): AttributeEntry
}