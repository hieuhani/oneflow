package vn.periscope.adapters.persistence.product

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.adapters.persistence.resource.ResourceRepository
import vn.periscope.ports.product.models.Product

object ProductRepository : ResourceRepository<Product, ProductEntity, Long, ProductTable>() {
    override val table = ProductTable

    override fun fromSqlResultRow(resultRow: ResultRow) = ProductEntity(
        id = resultRow[ProductTable.id].value,
        businessId = resultRow[ProductTable.businessId],
        taxonomy = resultRow[ProductTable.taxonomy],
        managementMethodology = resultRow[ProductTable.managementMethodology],
        code = resultRow[ProductTable.code],
        name = resultRow[ProductTable.name],
        brandId = resultRow[ProductTable.brandId],
        industryId = resultRow[ProductTable.industryId],
        createdAt = resultRow[ProductTable.createdAt],
        updatedAt = resultRow[ProductTable.updatedAt],
    )

    override fun toInsertStatement(entry: Product): ProductTable.(InsertStatement<Number>) -> Unit = {
        it[businessId] = entry.businessId
        it[taxonomy] = ProductTaxonomy.valueOf(entry.taxonomy.name)
        it[managementMethodology] = ProductManagementMethodology.valueOf(entry.managementMethodology.name)
        it[code] = entry.code
        it[name] = entry.name
        it[brandId] = entry.brandId
        it[industryId] = entry.industryId
        it[createdAt] = entry.createdAt
        it[updatedAt] = entry.updatedAt
    }

    override fun toUpdateStatement(entry: Product): ProductTable.(UpdateStatement) -> Unit = {
        it[brandId] = entry.brandId
        it[industryId] = entry.industryId
        it[updatedAt] = entry.updatedAt
    }
}