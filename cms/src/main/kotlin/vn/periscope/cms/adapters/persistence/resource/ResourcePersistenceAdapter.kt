package vn.periscope.cms.adapters.persistence.resource

import org.jetbrains.exposed.dao.id.IdTable
import vn.periscope.cms.ports.resource.models.FilterEntry
import vn.periscope.cms.ports.resource.models.Paging
import vn.periscope.cms.ports.resource.output.CrudResourceEntryPort

internal class ResourcePersistenceAdapter<Entry, Entity : ResourceEntity<Entry>, ID : Comparable<ID>>(
    private val resourceRepository: ResourceRepository<Entry, Entity, ID, IdTable<ID>>
) : CrudResourceEntryPort<Entry, ID> {
    override suspend fun create(resource: Entry): Entry {
        return resourceRepository.create(resource).toEntry()
    }

    override suspend fun delete(id: ID): Boolean {
        return resourceRepository.delete(id)
    }

    override suspend fun update(id: ID, resource: Entry): Entry {
        return resourceRepository.update(id, resource).toEntry()
    }

    override suspend fun getById(id: ID): Entry {
        return resourceRepository.get(id).toEntry()
    }

    override suspend fun getAllResources(): List<Entry> {
        return resourceRepository.getAll().map { it.toEntry() }
    }

    override suspend fun filter(filter: FilterEntry): Paging<Entry> {
        val entityRecordSet = resourceRepository.filter(filter)
        return Paging(
            records = entityRecordSet.records.map { it.toEntry() },
            limit = filter.limit,
            offset = filter.offset,
            totalRecords = entityRecordSet.totalRecords,
        )
    }
}