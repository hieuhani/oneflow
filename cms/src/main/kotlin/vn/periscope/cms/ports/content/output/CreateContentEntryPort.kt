package vn.periscope.cms.ports.content.output

import vn.periscope.cms.ports.content.models.ContentEntry

interface CreateContentEntryPort {
    fun create(content: ContentEntry): ContentEntry
}