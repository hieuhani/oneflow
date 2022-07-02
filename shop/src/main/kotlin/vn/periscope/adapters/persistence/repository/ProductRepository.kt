package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.entity.ProductTable
import java.time.Instant

class ProductRepository(
    private val productTable: ProductTable
) {

    private fun fromSqlResultRow(resultRow: ResultRow) = ProductEntity(
        id = resultRow[ProductTable.id].value,
        businessId = resultRow[ProductTable.businessId],
        type = resultRow[ProductTable.type],
        name = resultRow[ProductTable.name],
        photoId = resultRow[ProductTable.photoId],
        brandId = resultRow[ProductTable.brandId],
        industryId = resultRow[ProductTable.industryId],
        price = resultRow[ProductTable.price],
        createdAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductTable.createdAt].toEpochMilli()),
        updatedAt = kotlinx.datetime.Instant.fromEpochMilliseconds(resultRow[ProductTable.updatedAt].toEpochMilli()),
    )

    fun getById(_businessId: Long, _id: Long): ProductEntity? {
        return productTable.select { ProductTable.businessId eq _businessId and (ProductTable.id eq _id) and (ProductTable.deleted eq false) }
            .firstNotNullOfOrNull { fromSqlResultRow(it) }
    }

    fun insert(entity: ProductEntity): ProductEntity? {
        val id = productTable.insertAndGetId {
            it[businessId] = entity.businessId
            it[type] = entity.type
            it[name] = entity.name
            it[photoId] = entity.photoId
            it[brandId] = entity.brandId
            it[industryId] = entity.industryId
            it[price] = entity.price
            it[createdAt] = Instant.ofEpochMilli(entity.createdAt.toEpochMilliseconds())
            it[updatedAt] = Instant.ofEpochMilli(entity.updatedAt.toEpochMilliseconds())
            it[deleted] = false
        }
        return getById(entity.businessId, id.value)
    }

//    fun update(_businessId: Long, _id: Long, entry: ProductEntry): ProductEntity {
//        val entity = getById(_businessId, _id) ?: throw Error("Update product failure, not found product id=$_id")
//        entity.type = entry.type
//        entity.name = entry.name
//        entity.photoId = entry.photoId
//        entity.brandId = entry.brandId
//        entity.industryId = entry.industryId
//        entity.updatedAt = Instant.now()
//        return entity
//    }
//
//
//    fun delete(_businessId: Long, _id: Long) {
//        val entity = getById(_businessId, _id) ?: throw Error("Delete product failure, not found product id=$_id")
//        entity.delete()
//    }
}