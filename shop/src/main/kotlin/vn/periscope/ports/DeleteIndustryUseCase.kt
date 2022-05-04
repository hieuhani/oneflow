package vn.periscope.ports

interface DeleteIndustryUseCase {
    fun delete(businessId: Long, id: Long)
}