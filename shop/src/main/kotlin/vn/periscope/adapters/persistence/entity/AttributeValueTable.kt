package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.sql.Table

object AttributeValueTable : Table("attribute_value") {
    val attributeId = long("attribute_id").index()
    val value = varchar("value", 64).index()

    override val primaryKey = PrimaryKey(attributeId, value)
}