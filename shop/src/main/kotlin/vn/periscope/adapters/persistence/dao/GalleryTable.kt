package vn.periscope.adapters.persistence.dao


import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.GalleryTargetObjectType
import java.time.Instant
import java.util.*

object GalleryTable : LongIdTable("gallery") {
    val nid = uuid("nid").default(UUID.randomUUID())
    val businessId = long("business_id")
    val targetObjectType = enumerationByName("target_object_type", 32, GalleryTargetObjectType::class)
    val targetObjectId = long("target_object_id")
    val storeId = long("store_id")
    val default = bool("default").default(false)
    val position = integer("position").default(1)
    val disabled = bool("disabled").default(false)
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}