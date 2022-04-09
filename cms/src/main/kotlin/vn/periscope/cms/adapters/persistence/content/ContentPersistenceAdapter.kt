package vn.periscope.cms.adapters.persistence.content

import vn.periscope.cms.ports.content.CreateContentEntryPort
import vn.periscope.cms.ports.content.GetContentEntryPort
import vn.periscope.cms.ports.content.GetContentsEntryPort
import vn.periscope.cms.ports.content.models.ContentEntry

internal class ContentPersistenceAdapter(
    private val contentRepository: ContentRepository,
) : GetContentsEntryPort,
    GetContentEntryPort,
    CreateContentEntryPort {
    override fun findAll(): List<ContentEntry> {
        return contentRepository.getAll().map { it.toEntry() }
    }

    override fun findById(id: Long): ContentEntry {
        return contentRepository.get(id).toEntry()
    }

    override fun create(content: ContentEntry): ContentEntry {
        return contentRepository.create(content).toEntry()
    }
}