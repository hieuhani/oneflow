package vn.periscope.ports.out

import vn.periscope.ports.models.ProductEntry

interface GetProductEntryPort {

    fun getNextSeriesId(): Long

    fun getById(businessId : Long, id: Long): ProductEntry
}