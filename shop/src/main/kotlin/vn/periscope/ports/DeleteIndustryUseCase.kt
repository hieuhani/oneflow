package vn.periscope.ports

interface DeleteIndustryUseCase {
    suspend fun delete(businessId: Long, id: Long)
}