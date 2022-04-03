package vn.periscope.storage.adapters.api.routes.storage.dto

import kotlinx.serialization.Serializable
import vn.periscope.storage.ports.file.models.FileEntry

@Serializable
data class FileResponseDto(
    val id: Long,
    val name: String,
    val contentType: String,
    val fileName: String,
    val filePath: String,
    val ownerId: Long,
    val serviceName: String,
    val description: String? = null,
    val entityType: String,
    val entityId: Long,
) {
    companion object {
        fun fromDomainModel(entry: FileEntry) = with(entry) {
            FileResponseDto(
                id = id!!,
                name,
                contentType,
                fileName,
                filePath,
                ownerId,
                serviceName,
                description,
                entityType,
                entityId,
            )
        }
    }
}