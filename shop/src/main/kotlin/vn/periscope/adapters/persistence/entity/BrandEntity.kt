package vn.periscope.adapters.persistence.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.time.Instant

class BrandEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BrandEntity>(BrandTable)

    var businessId by BrandTable.businessId
    var name: String by BrandTable.name
    var logoId: Long by BrandTable.logoId
    var countryId: Long by BrandTable.countryId
    var createdAt: Instant by BrandTable.createdAt
    var updatedAt: Instant by BrandTable.updatedAt
}