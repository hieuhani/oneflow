package vn.periscope.ports.out

import vn.periscope.core.domain.Attribute

interface GetAttributeEntryPoint {
    fun findById(id: Long): Attribute?
    fun getNextSeriesIds(quantity: Int): Set<Long>
}