package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GalleryEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GalleryEntity>(GalleryTable)
    val nid by GalleryTable.nid
    val businessId by GalleryTable.businessId
    val targetObjectType by GalleryTable.targetObjectType
    val targetObjectId by GalleryTable.targetObjectId
    val storeId by GalleryTable.storeId
    val default by GalleryTable.default
    val position by GalleryTable.position
    val disabled by GalleryTable.disabled
    val createdAt by GalleryTable.createdAt
    val updatedAt by GalleryTable.updatedAt
}