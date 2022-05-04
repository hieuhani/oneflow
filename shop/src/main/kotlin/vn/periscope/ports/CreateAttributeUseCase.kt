package vn.periscope.ports

import vn.periscope.core.domain.Attribute
import vn.periscope.ports.models.AttributeEntry

interface CreateAttributeUseCase {
    fun create(entries: List<AttributeEntry>): List<Attribute>
}