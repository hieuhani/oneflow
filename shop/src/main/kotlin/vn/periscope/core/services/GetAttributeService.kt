package vn.periscope.core.services

import vn.periscope.ports.GetAttributeUseCase
import vn.periscope.ports.out.GetAttributeEntryPoint

class GetAttributeService(
    private val getAttributeEntryPoint: GetAttributeEntryPoint
) : GetAttributeUseCase{
    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return getAttributeEntryPoint.getNextSeriesIds(quantity)
    }
}