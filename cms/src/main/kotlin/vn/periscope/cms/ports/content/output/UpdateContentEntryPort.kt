package vn.periscope.cms.ports.content.output

import vn.periscope.cms.ports.content.models.ContentEntry

interface UpdateContentEntryPort {
    fun update(id: Long, content: ContentEntry): ContentEntry
}