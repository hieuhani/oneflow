package vn.periscope.cms.adapters.persistence.resource

import org.jetbrains.exposed.dao.id.IdTable
import vn.periscope.cms.ports.resource.CreateResourceEntry

internal class ResourcePersistenceAdapter<Entry, Entity>(
    private val resourceRepository: ResourceRepository<Entry, Entity, Long, IdTable<Long>>
) : CreateResourceEntry<Entry> {
    override fun createResource(resourceEntry: Entry): Entry {
        TODO("Not yet implemented")
    }
}