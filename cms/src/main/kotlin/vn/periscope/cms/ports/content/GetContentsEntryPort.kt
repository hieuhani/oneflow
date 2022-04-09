package vn.periscope.cms.ports.content

import vn.periscope.cms.ports.content.models.ContentEntry

interface GetContentsEntryPort {
    fun findAll(): List<ContentEntry>
}