package vn.periscope.adapters.persistence.entity


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.GalleryTargetObjectType

object GalleryTable : Table("gallery") {
    val id = long("id").uniqueIndex().entityId()
    val nid = uuid("nid")
    val businessId = long("business_id")
    val targetObjectType = enumerationByName("target_object_type", 32, GalleryTargetObjectType::class)
    val targetObjectId = long("target_object_id")
    val storeId = long("store_id")
    val position = integer("position")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}