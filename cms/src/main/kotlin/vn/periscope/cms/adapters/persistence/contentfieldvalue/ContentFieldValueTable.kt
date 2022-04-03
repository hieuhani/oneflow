package vn.periscope.cms.adapters.persistence.contentfieldvalue

import org.jetbrains.exposed.dao.id.LongIdTable
import vn.periscope.cms.adapters.persistence.content.ContentTable
import vn.periscope.cms.adapters.persistence.contenttypefield.ContentTypeFieldTable

object ContentFieldValueTable : LongIdTable(name = "content_field_values") {
    val value = text("value")
    val contentId = long("content_id").references(ContentTable.id)
    val contentTypeFieldId = long("content_type_field_id").references(ContentTypeFieldTable.id)
}