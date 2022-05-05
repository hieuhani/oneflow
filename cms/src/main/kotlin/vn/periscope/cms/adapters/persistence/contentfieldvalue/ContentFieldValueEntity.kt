package vn.periscope.cms.adapters.persistence.contentfieldvalue

import vn.periscope.cms.adapters.persistence.resource.ResourceEntity
import vn.periscope.cms.ports.contentfieldvalue.models.ContentFieldValueEntry

data class ContentFieldValueEntity(
    val id: Long? = null,
    val value: String,
    val contentId: Long,
    val contentTypeFieldId: Long,
) : ResourceEntity<ContentFieldValueEntry> {
    override fun toEntry() = ContentFieldValueEntry(
        id,
        value,
        contentId,
        contentTypeFieldId,
    )
}