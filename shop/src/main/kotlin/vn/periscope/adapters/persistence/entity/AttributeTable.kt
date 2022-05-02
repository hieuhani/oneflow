package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.AttributeReferType

object AttributeTable : Table(name = "attribute") {
    val id = long("id").uniqueIndex()
    val businessId = long("business_id").index()
    val referType = enumerationByName("refer_type", 32, AttributeReferType::class).index()
    val referId = long("refer_id").index()
    val name = varchar("name", 32)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
    val nid = uuid("nid").index()
}