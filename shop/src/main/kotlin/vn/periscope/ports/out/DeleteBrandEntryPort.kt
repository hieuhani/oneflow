package vn.periscope.ports.out

interface DeleteBrandEntryPort {
    fun delete(businessId: Long, id: Long)
}