package vn.periscope.cms.adapters.persistence.contenttypefield

import org.jetbrains.exposed.dao.id.LongIdTable
import vn.periscope.cms.adapters.persistence.contenttype.ContentTypeTable
import vn.periscope.cms.ports.contenttypefield.models.ContentTypeFieldType

object ContentTypeFieldTable : LongIdTable("content_type_fields") {
    val label = varchar("label", 255)
    val name = varchar("name", 255)
    val order = integer("order").default(0)
    val required = bool("required").default(false)
    val type = enumerationByName("type", 20, ContentTypeFieldType::class).default(ContentTypeFieldType.UNSPECIFIED)
    val contentTypeId = long("content_type_id").references(ContentTypeTable.id)
}