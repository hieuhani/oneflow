package vn.periscope.cms.adapters.persistence.contenttypefield

import vn.periscope.cms.adapters.persistence.resource.ResourceEntity
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldType

data class ContentTypeFieldEntity(
    val id: Long? = null,
    val label: String,
    val name: String,
    val order: Int,
    val required: Boolean,
    val type: ContentTypeFieldType,
    val contentTypeId: Long,
) : ResourceEntity<ContentTypeFieldEntry> {
    override fun toEntry() = ContentTypeFieldEntry(
        id,
        label,
        name,
        order,
        required,
        type,
        contentTypeId,
    )
}