package vn.periscope.adapters.persistence.entity


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.ObjectReferenceType

object GalleryTable : Table("gallery") {
    val id = long("id").uniqueIndex().entityId()
    val nid = uuid("nid").index()
    val businessId = long("business_id").index()
    val referenceType = enumerationByName("target_object_type", 32, ObjectReferenceType::class).index()
    val referenceId = long("target_object_id").index()
    val storeId = long("store_id")
    val position = integer("position")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}