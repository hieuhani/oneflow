package vn.periscope.cms.ports.content.output

import vn.periscope.cms.ports.content.models.ContentEntry

interface GetContentsEntryPort {
    fun findAll(): List<ContentEntry>
}