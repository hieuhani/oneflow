package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import vn.periscope.adapters.persistence.entity.ProductEntity
import vn.periscope.adapters.persistence.entity.ProductTable
import vn.periscope.core.domain.Product
import java.time.Instant

object ProductRepository {

    private val table = ProductTable
    fun findById(id: Long): ProductEntity {
        return table.select { table.id eq id }.first().let { fromSqlResultRow(it) }
    }

    private fun fromSqlResultRow(resultRow: ResultRow) = ProductEntity(
        id = resultRow[ProductTable.id].value,
        nid = resultRow[ProductTable.nid],
        businessId = resultRow[ProductTable.businessId],
        taxonomy = resultRow[ProductTable.taxonomy],
        managementMethodology = resultRow[ProductTable.managementMethodology],
        name = resultRow[ProductTable.name],
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
            it[taxonomy] = entity.taxonomy
            it[managementMethodology] = entity.managementMethodology
            it[name] = entity.name
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

    fun filter(

    ): List<ProductEntity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }

    fun search(): List<ProductEntity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }

    fun delete(id: Long, businessId: Long): Boolean {
        val affectedRows = table.update({ table.id eq id and (table.businessId eq businessId) }, null, {
            it[deleted] = true
        })
        return affectedRows > 0
    }

    fun findByIdAndBusinessId(id: Long, businessId: Long): ProductEntity {
        return table.select { table.id eq id and (table.businessId eq businessId) }.first().let { fromSqlResultRow(it) }
    }
}