package vn.periscope.adapters.persistence.product

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.ports.product.models.ProductEntry
import java.time.Instant

data class ProductEntity(
    val id: Long,
    val businessId: Long,
    val taxonomy: ProductTaxonomy,
    val managementMethodology: ProductManagementMethodology,
    val code: String,
    val name: String,
    val brandId: Long,
    val industryId: Long,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object
}

internal fun ProductEntity.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[ProductTable.businessId] = businessId
    it[ProductTable.taxonomy] = ProductTaxonomy.valueOf(taxonomy.name)
    it[ProductTable.managementMethodology] = ProductManagementMethodology.valueOf(managementMethodology.name)
    it[ProductTable.code] = code
    it[ProductTable.name] = name
    it[ProductTable.brandId] = brandId
    it[ProductTable.industryId] = industryId
    it[ProductTable.createdAt] = createdAt
    it[ProductTable.updatedAt] = updatedAt
}

internal fun ProductEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = ProductEntity(
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

internal fun ProductEntity.toEntry() = ProductEntry(
    id,
    businessId,
    ProductEntry.ProductTaxonomy.valueOf(taxonomy.name),
    ProductEntry.ProductManagementMethodology.valueOf(taxonomy.name),
    code,
    name,
    brandId,
    industryId,
    listOf(),
    listOf(),
    listOf(),
    createdAt,
    updatedAt
)
