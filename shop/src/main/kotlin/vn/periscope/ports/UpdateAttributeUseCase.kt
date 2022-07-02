package vn.periscope.ports

import vn.periscope.core.domain.Attribute
import vn.periscope.ports.models.AttributeEntry

interface UpdateAttributeUseCase {
    fun update(businessId: Long, id: Long, entry: AttributeEntry): Attribute
}