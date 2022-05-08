package vn.periscope.cms.adapters.api.routes.contenttypefield

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldCardinality
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldExtraData
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldType

@Serializable
data class ContentTypeFieldRequestDto(
    val label: String,
    val name: String,
    val order: Int,
    val required: Boolean,
    val type: ContentTypeFieldType,
    val cardinality: ContentTypeFieldCardinality,
    val contentTypeId: Long,
    val extraData: ContentTypeFieldExtraData?,
) {
    fun toDomainModel() = ContentTypeFieldEntry(
        id = null,
        label,
        name,
        order,
        required,
        type,
        cardinality,
        contentTypeId,
        extraData,
    )
}