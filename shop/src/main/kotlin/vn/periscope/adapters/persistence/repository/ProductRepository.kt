package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.entity.ProductTable
import java.time.Instant

class ProductRepository(
    private val table: ProductTable
) {

    fun findById(id: Long): ProductEntity {
        return table.select { table.id eq id and (table.deleted eq false) }.first().let { fromSqlResultRow(it) }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = ProductEntity(
        id = resultRow[ProductTable.id],
        nid = resultRow[ProductTable.nid],
        businessId = resultRow[ProductTable.businessId],
        type = resultRow[ProductTable.type],
        name = resultRow[ProductTable.name],
        photoId = resultRow[ProductTable.brandId],
        brandId = resultRow[ProductTable.brandId],
        industryId = resultRow[ProductTable.industryId],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductTable.updatedAt].toEpochMilli()),
    )

    fun insert(entity: ProductEntity) {
        table.insert {
            it[id] = entity.id
            it[nid] = entity.nid
            it[businessId] = entity.businessId
            it[type] = entity.type
            it[name] = entity.name
            it[photoId] = entity.photoId
            it[brandId] = entity.brandId
            it[industryId] = entity.industryId
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
            it[deleted] = false
        }
    }

    fun update(id: Long, entity: ProductEntity) {
        table.update({ table.id eq id }, 1, {
            it[name] = entity.name
            it[brandId] = entity.brandId
            it[industryId] = entity.industryId
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
        })
    }

    fun filter(businessId: Long): List<ProductEntity> {
        return table.select { table.businessId eq businessId and (table.deleted eq false) }.map { fromSqlResultRow(it) }
    }

    fun delete(id: Long, businessId: Long) {
        table.update({ table.id eq id and (table.businessId eq businessId) }, null, {
            it[deleted] = true
        })
    }

    fun findById(id: Long, businessId: Long): ProductEntity {
        return table.select { table.id eq id and (table.businessId eq businessId) and (table.deleted eq false) }.first()
            .let { fromSqlResultRow(it) }
    }
}