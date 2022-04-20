package vn.periscope.adapters.persistence.gallery


import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant

object GalleryTable : LongIdTable("gallery") {
    val businessId = long("business_id")
    val targetObjectType = enumerationByName("target_object_type", 32, GalleryTargetObjectType::class)
    val targetObjectId = long("target_object_id")
    val storeId = long("store_id")
    val default = bool("default").default(false)
    val position = integer("position").default(1)
    val disabled = bool("disabled").default(false)
    val mediaType = enumerationByName("media_type", 32, GalleryMediaType::class)
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}