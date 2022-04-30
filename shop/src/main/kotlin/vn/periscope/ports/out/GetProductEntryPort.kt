package vn.periscope.ports.out

import vn.periscope.core.domain.Product

interface GetProductEntryPort {

    fun getNextSeriesId(): Long

    fun findById(id: Long, businessId : Long): Product?
}