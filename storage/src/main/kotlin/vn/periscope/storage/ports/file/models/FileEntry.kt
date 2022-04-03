package vn.periscope.storage.ports.file.models

data class FileEntry(
    val id: Long? = null,
    val name: String,
    val contentType: String,
    val fileName: String,
    val filePath: String,
    val ownerId: Long,
    val serviceName: String,
    val description: String? = null,
    val entityType: String,
    val entityId: Long,
)