package vn.periscope.ports.out

import vn.periscope.core.domain.ProductAttribute

interface GetProductAttributeEntryPoint {
    fun findById(id: Long): ProductAttribute?
    fun getNextSeriesIds(quantity: Int): Set<Long>
}