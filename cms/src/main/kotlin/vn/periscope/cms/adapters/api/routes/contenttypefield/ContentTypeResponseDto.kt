package vn.periscope.cms.adapters.api.routes.contenttypefield

import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldEntry
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldType

@Serializable
data class ContentTypeFieldResponseDto(
    val id: Long,
    val label: String,
    val name: String,
    val order: Int,
    val required: Boolean,
    val type: ContentTypeFieldType,
    val contentTypeId: Long,
) {
    companion object {
        fun fromDomainModel(model: ContentTypeFieldEntry) = with(model) {
            ContentTypeFieldResponseDto(
                id = id!!,
                label,
                name,
                order,
                required,
                type,
                contentTypeId,
            )
        }
    }
}