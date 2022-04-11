package vn.periscope.cms.adapters.persistence.content

import vn.periscope.cms.ports.content.CreateContentEntryPort
import vn.periscope.cms.ports.content.GetContentEntryPort
import vn.periscope.cms.ports.content.GetContentsEntryPort
import vn.periscope.cms.ports.content.UpdateContentEntryPort
import vn.periscope.cms.ports.content.models.ContentEntry

internal class ContentPersistenceAdapter(
    private val contentRepository: ContentRepository,
) : GetContentsEntryPort,
    GetContentEntryPort,
    CreateContentEntryPort,
    UpdateContentEntryPort {
    override fun findAll(): List<ContentEntry> {
        return contentRepository.getAll().map { it.toEntry() }
    }

    override fun findById(id: Long): ContentEntry {
        return contentRepository.get(id).toEntry()
    }

    override fun create(content: ContentEntry): ContentEntry {
        return contentRepository.create(content).toEntry()
    }

    override fun update(id: Long, content: ContentEntry): ContentEntry {
        return contentRepository.update(id, content).toEntry()
    }
}