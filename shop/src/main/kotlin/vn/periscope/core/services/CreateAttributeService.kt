package vn.periscope.core.services

import kotlinx.datetime.Clock
import vn.periscope.core.domain.Attribute
import vn.periscope.ports.CreateAttributeUseCase
import vn.periscope.ports.models.AttributeEntry
import vn.periscope.ports.out.GetAttributeEntryPoint

class CreateAttributeService(
    private val getAttributeEntryPoint: GetAttributeEntryPoint
) : CreateAttributeUseCase {
    override fun create(entries: List<AttributeEntry>): List<Attribute> {
        val ids = getAttributeEntryPoint.getNextSeriesIds(entries.size)
        val galleries = mutableListOf<Attribute>()
        entries.forEachIndexed { index, attributeEntry ->
            galleries.add(
                Attribute(
                    id = ids.toMutableList()[index],
                    name = attributeEntry.name,
                    values = attributeEntry.values,
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now(),
                )
            )
        }
        return galleries
    }
}