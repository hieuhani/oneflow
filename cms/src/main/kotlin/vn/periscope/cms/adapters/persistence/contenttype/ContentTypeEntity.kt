package vn.periscope.cms.adapters.persistence.contenttype

import vn.periscope.cms.adapters.persistence.resource.ResourceEntity
import vn.periscope.cms.ports.contenttype.models.ContentTypeEntry

data class ContentTypeEntity(
    val id: Long? = null,
    val name: String,
    val machineName: String,
    val description: String,
) : ResourceEntity<ContentTypeEntry> {
    override fun toEntry() = ContentTypeEntry(
        id,
        name,
        machineName,
        description,
    )
}