package vn.periscope.ports

interface DeleteBrandUseCase {
    suspend fun delete(id: Long, businessId: Long)
}
