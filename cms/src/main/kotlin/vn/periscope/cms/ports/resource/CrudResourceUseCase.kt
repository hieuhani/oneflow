package vn.periscope.cms.ports.resource

import vn.periscope.cms.ports.resource.models.FilterEntry
import vn.periscope.cms.ports.resource.models.Paging

interface CrudResourceUseCase<R, ID> {
    suspend fun create(resource: R): R
    suspend fun delete(id: ID): Boolean
    suspend fun update(id: ID, resource: R): R
    suspend fun getById(id: ID): R
    suspend fun getAllResources(): List<R>
    suspend fun filter(filter: FilterEntry?): Paging<R>
}