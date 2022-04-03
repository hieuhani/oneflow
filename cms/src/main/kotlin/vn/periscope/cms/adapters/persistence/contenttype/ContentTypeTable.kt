package vn.periscope.cms.adapters.persistence.contenttype

import org.jetbrains.exposed.dao.id.LongIdTable

object ContentTypeTable : LongIdTable(name = "content_types") {
    val name = varchar("name", 255)
    val machineName = varchar("machine_name", 255).uniqueIndex()
    val description = text("description")
}