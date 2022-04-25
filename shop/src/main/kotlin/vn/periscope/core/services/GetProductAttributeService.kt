package vn.periscope.core.services

import vn.periscope.ports.GetProductAttributeUseCase
import vn.periscope.ports.out.GetProductAttributeEntryPoint

class GetProductAttributeService(
    private val getProductAttributeEntryPoint: GetProductAttributeEntryPoint
) : GetProductAttributeUseCase{
    override fun getNextSeriesIds(quantity: Int): Set<Long> {
        return getProductAttributeEntryPoint.getNextSeriesIds(quantity)
    }
}