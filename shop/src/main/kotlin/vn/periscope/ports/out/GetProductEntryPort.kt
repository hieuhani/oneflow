package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface GetProductEntryPort {
    fun findById(id: Long): Product

    fun getNextSeriesId(): Long

    fun findByIdAndBusinessId(id: Long, businessId : Long): Product?
}