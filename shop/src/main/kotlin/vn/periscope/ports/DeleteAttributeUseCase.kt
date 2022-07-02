package vn.periscope.ports

interface DeleteAttributeUseCase {
    fun delete(businessId: Long, id: Long)
}