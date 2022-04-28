package vn.periscope.ports

interface DeleteProductUseCase {
    suspend fun delete(id: Long, businessId: Long)
}