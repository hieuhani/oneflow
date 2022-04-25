package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.*
import vn.periscope.adapters.persistence.dao.ProductEntity
import vn.periscope.adapters.persistence.dao.ProductTable

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
        createdAt = resultRow[ProductTable.createdAt],
        updatedAt = resultRow[ProductTable.updatedAt],
    )

    fun insert(entity: ProductEntity): ProductEntity {
        val id = table.insertAndGetId {
            it[nid] = entity.nid
            it[businessId] = entity.businessId
            it[taxonomy] = entity.taxonomy
            it[managementMethodology] = entity.managementMethodology
            it[name] = entity.name
            it[brandId] = entity.brandId
            it[industryId] = entity.industryId
            it[createdAt] = entity.createdAt
            it[updatedAt] = entity.createdAt
        }
        return findById(id.value)
    }

    fun update(id: Long, entity: ProductEntity): ProductEntity {
        val affectedRows = table.update({ table.id eq id }, null, {
            it[name] = entity.name
            it[brandId] = entity.brandId
            it[industryId] = entity.industryId
            it[updatedAt] = entity.updatedAt
        })
        if (affectedRows == 1) {
            return findById(id)
        }
        throw RuntimeException("Product update failed")
    }

    fun filter(): List<ProductEntity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }

    fun search(): List<ProductEntity> {
        return table.selectAll().map { fromSqlResultRow(it) }
    }

    fun delete(id: Long): Boolean {
        return table.deleteWhere {
            table.id eq id
        } == 1
    }
}