package vn.periscope.cms.ports.resource

interface CrudResourceUseCase<R, ID> {
    suspend fun create(resource: R): R
    suspend fun delete(id: ID): Boolean
    suspend fun update(id: ID, resource: R): R
    suspend fun getById(id: ID): R
    suspend fun getAllResources(): List<R>
}