package vn.periscope.ports.out

interface CrudResourceEntryPort<R, ID> {
    suspend fun create(resource: R): R
    suspend fun delete(id: ID): Boolean
    suspend fun update(id: ID, resource: R): R
    suspend fun findById(id: ID): R
    suspend fun findAll(): List<R>
}