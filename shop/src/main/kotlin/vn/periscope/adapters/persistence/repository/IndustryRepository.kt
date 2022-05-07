package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.and
import vn.periscope.adapters.persistence.entity.IndustryEntity
import vn.periscope.adapters.persistence.entity.IndustryTable
import vn.periscope.ports.models.IndustryEntry
import java.time.Instant

object IndustryRepository {

    fun getById(_businessId: Long, _id: Long): IndustryEntity? {
        return IndustryEntity.find { (IndustryTable.businessId eq _businessId) and (IndustryTable.id eq _id) }
            .firstOrNull()
    }

    fun insert(_businessId: Long, entry: IndustryEntry): IndustryEntity {
        return IndustryEntity.new {
            businessId = _businessId
            name = entry.name
            machineName = entry.machineName
            createdAt = Instant.now()
            updatedAt = Instant.now()
        }
    }

    fun update(_businessId: Long, _id: Long, entry: IndustryEntry): IndustryEntity {
        val entity = getById(_businessId, _id) ?: throw Error("Update industry failure, not found industry id=$_id")
        entity.name = entry.name
        entity.machineName = entry.name
        entity.updatedAt = Instant.now()
        return entity
    }

    fun delete(_businessId: Long, _id: Long) {
        val entity = getById(_businessId, _id) ?: throw Error("Update industry failure, not found industry id=$_id")
        entity.delete()
    }

    fun find(_businessId: Long): List<IndustryEntity> {
        return IndustryEntity.find { (IndustryTable.businessId eq _businessId) }.toList()
    }
}
