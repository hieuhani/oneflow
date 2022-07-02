package vn.periscope.core.services

import vn.periscope.core.domain.Attribute
import vn.periscope.ports.UpdateAttributeUseCase
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.out.UpdateAttributeEntryPort

class UpdateAttributeService(
    private val updateAttributeEntryPort: UpdateAttributeEntryPort
) : UpdateAttributeUseCase {

    override fun update(businessId: Long, id: Long, entry: AttributeEntry): Attribute {
        TODO("Not yet implemented")
    }
}