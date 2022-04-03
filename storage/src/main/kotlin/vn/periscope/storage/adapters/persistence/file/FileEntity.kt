package vn.periscope.storage.adapters.persistence.file

import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.storage.ports.file.models.FileEntry

data class FileEntity(
    val id: Long,
    val name: String,
    val contentType: String,
    val fileName: String,
    val filePath: String,
    val ownerId: Long,
    val description: String? = null,
    val serviceName: String,
    val entityType: String,
    val entityId: Long,
)

internal fun FileEntry.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[FileTable.name] = name
    it[FileTable.contentType] = contentType
    it[FileTable.fileName] = fileName
    it[FileTable.filePath] = filePath
    it[FileTable.ownerId] = ownerId
    it[FileTable.description] = description
    it[FileTable.serviceName] = serviceName
    it[FileTable.entityType] = entityType
    it[FileTable.entityId] = entityId
}

internal fun FileEntity.toEntry() = FileEntry(
    id,
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