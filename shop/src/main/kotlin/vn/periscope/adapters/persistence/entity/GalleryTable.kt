package vn.periscope.adapters.persistence.entity


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.GalleryReferType

object GalleryTable : Table("gallery") {
    val id = long("id").uniqueIndex()
    val nid = uuid("nid").index()
    val businessId = long("business_id").index()
    val referType = enumerationByName("refer_type", 32, GalleryReferType::class).index()
    val referId = long("refer_id").index()
    val storeId = long("store_id")
    val position = integer("position")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}