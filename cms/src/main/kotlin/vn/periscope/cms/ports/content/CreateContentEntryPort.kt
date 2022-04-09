package vn.periscope.cms.ports.content

import vn.periscope.cms.ports.content.models.ContentEntry

interface CreateContentEntryPort {
    fun create(content: ContentEntry): ContentEntry
}