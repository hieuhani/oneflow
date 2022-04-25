package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.adapters.persistence.dao.GalleryEntity
import vn.periscope.adapters.persistence.dao.GalleryTable
import vn.periscope.adapters.persistence.resource.ResourceRepository
import vn.periscope.ports.product.models.GalleryEntry
import vn.periscope.share.statics.GalleryTargetObjectType

object GalleryRepository : ResourceRepository<GalleryEntry, GalleryEntity, Long, GalleryTable>() {
    override val table = GalleryTable

    override fun fromSqlResultRow(resultRow: ResultRow) = GalleryEntity(
        id = resultRow[GalleryTable.id].value,
        businessId = resultRow[GalleryTable.businessId],
        targetObjectType = resultRow[GalleryTable.targetObjectType],
        targetObjectId = resultRow[GalleryTable.targetObjectId],
        storeId = resultRow[GalleryTable.storeId],
        default = resultRow[GalleryTable.default],
        position = resultRow[GalleryTable.position],
        disabled = resultRow[GalleryTable.disabled],
        mediaType = resultRow[GalleryTable.mediaType],
        createdAt = resultRow[GalleryTable.createdAt],
        updatedAt = resultRow[GalleryTable.updatedAt],
    )

    override fun toInsertStatement(entry: GalleryEntry): GalleryTable.(InsertStatement<Number>) -> Unit = {
        it[businessId] = entry.businessId
        it[targetObjectType] = GalleryTargetObjectType.valueOf(entry.targetObjectType.name)
        it[targetObjectId] = entry.targetObjectId
        it[storeId] = entry.storeId
        it[default] = entry.default
        it[position] = entry.position
        it[disabled] = entry.disabled
        it[mediaType] = GalleryMediaType.valueOf(entry.mediaType.name)
        it[createdAt] = entry.createdAt
        it[updatedAt] = entry.updatedAt

    }

    override fun toUpdateStatement(entry: GalleryEntry): GalleryTable.(UpdateStatement) -> Unit = {
        it[default] = entry.default
        it[position] = entry.position
        it[disabled] = entry.disabled
        it[updatedAt] = entry.updatedAt
    }

    fun getByTargetObjectTypeAndTargetObjectId(
        targetObjectType: GalleryTargetObjectType,
        targetObjectId: Long
    ): List<GalleryEntity> {
        return table.select { table.targetObjectType eq targetObjectType }
            .andWhere { table.targetObjectId eq targetObjectId }.map { fromSqlResultRow(it) }
    }
}