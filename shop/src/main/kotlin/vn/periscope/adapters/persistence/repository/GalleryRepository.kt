package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.GalleryEntity
import vn.periscope.adapters.persistence.entity.GalleryTable
import vn.periscope.core.domain.Gallery
import vn.periscope.share.statics.GalleryTargetObjectType
import java.time.Instant

object GalleryRepository {
    private val table = GalleryTable

    fun fromSqlResultRow(resultRow: ResultRow) = GalleryEntity(
        id = resultRow[GalleryTable.id].value,
        nid = resultRow[GalleryTable.nid],
        businessId = resultRow[GalleryTable.businessId],
        targetObjectType = resultRow[GalleryTable.targetObjectType],
        targetObjectId = resultRow[GalleryTable.targetObjectId],
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
            it[targetObjectType] = entity.targetObjectType
            it[targetObjectId] = entity.targetObjectId
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
            this[GalleryTable.targetObjectType] = entity.targetObjectType
            this[GalleryTable.targetObjectId] = entity.targetObjectId
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

    fun mustFilter(
        ids: List<Long> = listOf(),
        targetObjectType: GalleryTargetObjectType = GalleryTargetObjectType.UNKNOWN,
        targetObjectIds: List<Long> = listOf(),
    ): List<GalleryEntity> {
        val query = table.selectAll()
        if (ids.isNotEmpty()) query.andWhere { table.id inList ids }
        if (targetObjectType != GalleryTargetObjectType.UNKNOWN) query.andWhere { table.targetObjectType eq targetObjectType }
        if (targetObjectIds.isNotEmpty()) query.andWhere { table.targetObjectId inList targetObjectIds }
        return query.map { fromSqlResultRow(it) }
    }

    fun shouldFilter(
        ids: List<Long> = listOf(),
        targetObjectType: GalleryTargetObjectType = GalleryTargetObjectType.UNKNOWN,
        targetObjectIds: List<Long> = listOf(),
    ): List<GalleryEntity> {
        val query = table.selectAll()
        if (ids.isNotEmpty()) query.orWhere { table.id inList ids }
        if (targetObjectType != GalleryTargetObjectType.UNKNOWN) query.orWhere { table.targetObjectType eq targetObjectType }
        if (targetObjectIds.isNotEmpty()) query.orWhere { table.targetObjectId inList targetObjectIds }
        return query.map { fromSqlResultRow(it) }
    }
}