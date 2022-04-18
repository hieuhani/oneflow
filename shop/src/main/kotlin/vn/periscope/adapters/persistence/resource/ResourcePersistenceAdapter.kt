package vn.periscope.adapters.persistence.resource

import org.jetbrains.exposed.dao.id.IdTable
import vn.periscope.ports.resource.output.CrudResourceEntryPort

internal class ResourcePersistenceAdapter<Entry, Entity: ResourceEntity<Entry>, ID : Comparable<ID>>(
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

    override suspend fun filter(): List<Entry> {
        return resourceRepository.getAll().map { it.toEntry() }
    }

    override suspend fun search(): List<Entry> {
        return resourceRepository.getAll().map { it.toEntry() }
    }
}