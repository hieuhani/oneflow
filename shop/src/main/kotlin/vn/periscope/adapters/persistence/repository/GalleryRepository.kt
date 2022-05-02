package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.GalleryEntity
import vn.periscope.adapters.persistence.entity.GalleryTable
import vn.periscope.core.domain.Gallery
import vn.periscope.share.statics.GalleryReferType
import java.time.Instant

class GalleryRepository(
    private val table: GalleryTable
) {
    fun fromSqlResultRow(resultRow: ResultRow) = GalleryEntity(
        id = resultRow[GalleryTable.id],
        nid = resultRow[GalleryTable.nid],
        businessId = resultRow[GalleryTable.businessId],
        referType = resultRow[GalleryTable.referType],
        referId = resultRow[GalleryTable.referId],
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
            it[referType] = entity.referType
            it[referId] = entity.referId
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
            this[GalleryTable.referType] = entity.referType
            this[GalleryTable.referId] = entity.referId
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

    fun findByReferIn(referType: GalleryReferType, referIds: List<Long>): List<GalleryEntity> {
        return table.select { table.referType eq referType and (table.referId inList referIds) }
            .map { fromSqlResultRow(it) }
    }

    fun findByRefer(referType: GalleryReferType, referId: Long): List<GalleryEntity> {
        return table.select { table.referType eq referType and (table.referId eq referId) }
            .map { fromSqlResultRow(it) }
    }
}