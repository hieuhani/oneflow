package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Attribute
import vn.periscope.ports.CreateAttributeUseCase
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.out.CreateAttributeEntryPort
import vn.periscope.ports.out.GetAttributeEntryPort

class CreateAttributeService(
    private val getAttributeEntryPort: GetAttributeEntryPort,
    private val createAttributeEntryPoint: CreateAttributeEntryPort,
) : CreateAttributeUseCase {
    override fun create(businessId: Long, referId: Long, entries: List<AttributeEntry>): List<Attribute> {
        if (entries.isEmpty()) return listOf()
        val ids = getAttributeEntryPort.getNextSeriesIds(entries.size)
        val attributes = mutableListOf<Attribute>()
        entries.forEachIndexed { index, attributeEntry ->
            attributes.add(
                Attribute(
                    id = ids.toMutableList()[index],
                    name = attributeEntry.name,
                    values = attributeEntry.values,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            )
        }
        createAttributeEntryPoint.create(businessId, referId, attributes)
        return attributes
    }
}