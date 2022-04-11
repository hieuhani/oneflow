package vn.periscope.cms.adapters.api.routes.dto
import kotlinx.serialization.Serializable
import vn.periscope.cms.ports.content.models.ContentEntry

@Serializable
data class ContentResponseDto(
    val id: Long,
    val title: String,
    val description: String,
    val userId: Long,
    val contentTypeId: Long,
) {
    companion object {
        fun fromDomainModel(model: ContentEntry) = with(model) {
            ContentResponseDto(
                id = id!!,
                title,
                description,
                userId = userId!!,
                contentTypeId,
            )
        }
    }
}