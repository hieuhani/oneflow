package vn.periscope.adapters.persistence.product

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.ports.product.models.Product
import java.time.Instant

data class ProductAttributeEntity(
    val id: Long,
    val businessId: Long,
    val productId: Long,
    val name: String,
    val values: Set<String>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    companion object
}

private const val SEPARATOR = ","

internal fun ProductAttributeEntity.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[ProductAttributeTable.businessId] = businessId
    it[ProductAttributeTable.productId] = productId
    it[ProductAttributeTable.name] = name
    it[ProductAttributeTable.values] = values.joinToString(SEPARATOR)
    it[ProductAttributeTable.createdAt] = createdAt
    it[ProductAttributeTable.updatedAt] = updatedAt
}

internal fun ProductAttributeEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = ProductAttributeEntity(
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

internal fun ProductAttributeEntity.toEntry() = ProductAttribute(
    id,
    businessId,
    Product.ProductTaxonomy.valueOf(taxonomy.name),
    Product.ProductManagementMethodology.valueOf(taxonomy.name),
    code,
    name,
    brandId,
    industryId,
    null,
    createdAt,
    updatedAt
)