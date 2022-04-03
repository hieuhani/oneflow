package vn.periscope.storage.adapters.persistence.file

import org.jetbrains.exposed.dao.id.LongIdTable

object FileTable : LongIdTable(name = "files") {
    val name = varchar("name", 255)
    val contentType = varchar("content_type", 155)
    val description = text("description").nullable()
    val fileName = varchar("file_name", 255)
    val filePath = varchar("file_path", 255)
    val ownerId = long("owner_id")
    val serviceName = varchar("service_name", 32)
    val entityType = varchar("entity_type", 155)
    val entityId = long("entity_id").default(0)
}
