package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CategoryEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CategoryEntity>(CategoryTable)

    var nid by CategoryTable.nid
    var businessId by CategoryTable.businessId
    var taxonomy by CategoryTable.taxonomy
    var name by CategoryTable.name
    var slug by CategoryTable.slug
    var status by CategoryTable.status
    var parentId by CategoryTable.parentId
    var createdAt by CategoryTable.createdAt
    var updatedAt by CategoryTable.updatedAt
}