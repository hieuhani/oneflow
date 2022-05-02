package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object AttributeValueTable : LongIdTable("attribute_value") {
    var attributeId = long("attribute_id").index()
    var value = varchar("value", 64)
}