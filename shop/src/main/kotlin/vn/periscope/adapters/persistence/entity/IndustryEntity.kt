package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class IndustryEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<IndustryEntity>(IndustryTable)

    var businessId by IndustryTable.businessId
    var name by IndustryTable.name
    var machineName by IndustryTable.machineName
    var createdAt by IndustryTable.createdAt
    var updatedAt by IndustryTable.updatedAt
}
