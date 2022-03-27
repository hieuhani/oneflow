package vn.periscope.storage.ports.file.models

data class FileEntry(
    val id: Long? = null,
    val name: String,
    val contentType: String,
    val fileName: String,
    val filePath: String,
    val ownerId: Long,
    val description: String? = null,
    val serviceName: String? = null,
    val entityType: String? = null,
    val entityId: Long? = null,
)