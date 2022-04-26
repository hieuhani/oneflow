package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.adapters.persistence.entity.GalleryEntity
import vn.periscope.adapters.persistence.entity.GalleryTable
import vn.periscope.core.domain.Gallery
import vn.periscope.ports.models.GalleryEntry
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
        createdAt = resultRow[GalleryTable.createdAt],
        updatedAt = resultRow[GalleryTable.updatedAt],
    )

    fun insert(entity: GalleryEntry) {
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

    fun update(id: Long, entity: Gallery) {
        table.update({ table.id eq id }, 1, {
            it[position] = entity.position
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        })
    }
}