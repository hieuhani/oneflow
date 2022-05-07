package vn.periscope.ports.out

interface DeleteIndustryEntryPort {
    fun delete(businessId: Long, id: Long)
}