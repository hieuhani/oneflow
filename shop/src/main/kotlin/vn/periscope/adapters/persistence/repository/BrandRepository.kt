package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.and
import vn.periscope.adapters.persistence.entity.BrandEntity
import vn.periscope.adapters.persistence.entity.BrandTable
import vn.periscope.ports.models.BrandEntry
import java.time.Instant

object BrandRepository {

    fun getById(_businessId: Long, _id: Long): BrandEntity? {
        return BrandEntity.find { (BrandTable.businessId eq _businessId) and (BrandTable.id eq _id) }.firstOrNull()
    }

    fun insert(_businessId: Long, entry: BrandEntry): BrandEntity {
        return BrandEntity.new {
            businessId = _businessId
            name = entry.name
            logoId = entry.logoId
            countryId = entry.countryId
            createdAt = Instant.now()
            updatedAt = Instant.now()

        }
    }

    fun update(_businessId: Long, _id: Long, entry: BrandEntry): BrandEntity {
        val entity = getById(_businessId, _id) ?: throw Error("Update brand failure, not found brand id=$_id")
        entity.name = entry.name
        entity.logoId = entry.logoId
        entity.countryId = entry.countryId
        entity.updatedAt = Instant.now()
        return entity
    }

    fun find(_businessId: Long): List<BrandEntity> {
        return BrandEntity.find { (BrandTable.businessId eq _businessId) }.toList()
    }

    fun delete(_businessId: Long, _id: Long) {
        val entity = getById(_businessId, _id) ?: throw Error("Update brand failure, not found brand id=$_id")
        entity.delete()
    }

    fun count(_businessId: Long): Long {
        return BrandEntity.find { (BrandTable.businessId eq _businessId) }.count()
    }
}