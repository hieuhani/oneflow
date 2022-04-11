package vn.periscope.cms.ports.content.output

import vn.periscope.cms.ports.content.models.ContentEntry

interface GetContentEntryPort {
    fun findById(id: Long): ContentEntry
}