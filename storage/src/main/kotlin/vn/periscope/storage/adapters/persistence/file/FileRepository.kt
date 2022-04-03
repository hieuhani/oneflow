package vn.periscope.storage.adapters.persistence.file

import org.jetbrains.exposed.sql.insert
import vn.periscope.storage.ports.file.models.FileEntry

class FileRepository {
    fun create(entry: FileEntry): FileEntity {
        val fileId = FileTable.insert {
            entry.toCreateSqlStatement(it)
        }[FileTable.id]
        return with(entry) {
            FileEntity(
                id = fileId.value,
                name,
                contentType,
                fileName,
                filePath,
                ownerId,
                description,
                serviceName,
                entityType,
                entityId
            )
        }
    }
}