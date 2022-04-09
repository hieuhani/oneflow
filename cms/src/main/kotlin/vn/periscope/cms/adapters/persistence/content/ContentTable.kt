package vn.periscope.cms.adapters.persistence.content

import org.jetbrains.exposed.dao.id.LongIdTable
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeTable

object ContentTable : LongIdTable(name = "content") {
    val title = varchar("title", 512)
    val description = text("description")
    val userId = long("user_id")
    val contentTypeId = long("content_type_id").references(ContentTypeTable.id)
}