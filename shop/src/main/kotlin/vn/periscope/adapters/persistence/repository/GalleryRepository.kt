package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.GalleryEntity
import vn.periscope.adapters.persistence.entity.GalleryTable
import vn.periscope.core.domain.Gallery
import vn.periscope.share.statics.ObjectReferenceType
import java.time.Instant

object GalleryRepository {
    private val table = GalleryTable

    fun fromSqlResultRow(resultRow: ResultRow) = GalleryEntity(
        id = resultRow[GalleryTable.id].value,
        nid = resultRow[GalleryTable.nid],
        businessId = resultRow[GalleryTable.businessId],
        referenceType = resultRow[GalleryTable.referenceType],
        referenceId = resultRow[GalleryTable.referenceId],
        storeId = resultRow[GalleryTable.storeId],
        position = resultRow[GalleryTable.position],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[GalleryTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[GalleryTable.updatedAt].toEpochMilli()),
    )

    fun insert(entity: GalleryEntity) {
        table.insert {
            it[id] = entity.id
            it[nid] = entity.nid
            it[businessId] = entity.businessId
            it[referenceType] = entity.referenceType
            it[referenceId] = entity.referenceId
            it[storeId] = entity.storeId
            it[position] = entity.position
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
    }

    fun batchInsert(entities: List<GalleryEntity>) {
        table.batchInsert(entities) { entity ->
            this[GalleryTable.id] = entity.id
            this[GalleryTable.nid] = entity.nid
            this[GalleryTable.businessId] = entity.businessId
            this[GalleryTable.referenceType] = entity.referenceType
            this[GalleryTable.referenceId] = entity.referenceId
            this[GalleryTable.storeId] = entity.storeId
            this[GalleryTable.position] = entity.position
            this[GalleryTable.createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            this[GalleryTable.updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        }
    }

    fun update(id: Long, entity: Gallery) {
        table.update({ table.id eq id }, 1, {
            it[position] = entity.position
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        })
    }

    fun findByReferences(referenceType: ObjectReferenceType, referenceIds: List<Long>): List<GalleryEntity> {
        return table.select { table.referenceType eq referenceType and (table.referenceId inList referenceIds) }
            .map { fromSqlResultRow(it) }
    }

    fun findByReference(referenceType: ObjectReferenceType, referenceId: Long): List<GalleryEntity> {
        return table.select { table.referenceType eq referenceType and (table.referenceId eq referenceId) }
            .map { fromSqlResultRow(it) }
    }


}