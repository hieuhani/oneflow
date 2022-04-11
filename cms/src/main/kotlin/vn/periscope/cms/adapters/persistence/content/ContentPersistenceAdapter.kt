package vn.periscope.cms.adapters.persistence.content

import vn.periscope.cms.ports.content.models.ContentEntry
import vn.periscope.cms.ports.content.output.*

internal class ContentPersistenceAdapter(
    private val contentRepository: ContentRepository,
) : GetContentsEntryPort,
    GetContentEntryPort,
    CreateContentEntryPort,
    UpdateContentEntryPort,
    DeleteContentEntryPort {
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

    override fun delete(id: Long): Boolean {
        return contentRepository.delete(id)
    }
}