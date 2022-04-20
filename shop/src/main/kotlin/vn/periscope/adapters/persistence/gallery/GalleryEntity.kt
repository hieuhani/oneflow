package vn.periscope.adapters.persistence.gallery

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.ports.gallery.models.GalleryEntry
import java.time.Instant

data class GalleryEntity(
    val id: Long,
    val businessId: Long,
    val targetObjectType: GalleryTargetObjectType,
    val targetObjectId: Long,
    val storeId: Long,
    val default: Boolean,
    val position: Int,
    val disabled: Boolean,
    val mediaType: GalleryMediaType,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    companion object
}

internal fun GalleryEntity.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[GalleryTable.businessId] = businessId
    it[GalleryTable.targetObjectType] = targetObjectType
    it[GalleryTable.targetObjectId] = targetObjectId
    it[GalleryTable.storeId] = storeId
    it[GalleryTable.default] = default
    it[GalleryTable.position] = position
    it[GalleryTable.disabled] = disabled
    it[GalleryTable.mediaType] = mediaType
    it[GalleryTable.createdAt] = createdAt
    it[GalleryTable.updatedAt] = updatedAt
}

internal fun GalleryEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = GalleryEntity(
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

internal fun GalleryEntity.toEntry() = GalleryEntry(
    id,
    businessId,
    GalleryEntry.TargetObjectType.valueOf(targetObjectType.name),
    targetObjectId,
    storeId,
    default,
    position,
    disabled,
    GalleryEntry.MediaType.valueOf(mediaType.name),
    createdAt,
    updatedAt
)