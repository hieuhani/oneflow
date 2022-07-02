package vn.periscope.core.services

import vn.periscope.ports.GetAttributeUseCase
import vn.periscope.ports.out.GetAttributeEntryPort

class GetAttributeService(
    private val getAttributeEntryPort: GetAttributeEntryPort
) : GetAttributeUseCase{
    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return getAttributeEntryPort.getNextSeriesIds(quantity)
    }
}