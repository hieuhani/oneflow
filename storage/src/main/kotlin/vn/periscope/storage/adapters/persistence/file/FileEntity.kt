package vn.periscope.storage.adapters.persistence.file

data class FileEntity(
    val id: Long,
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