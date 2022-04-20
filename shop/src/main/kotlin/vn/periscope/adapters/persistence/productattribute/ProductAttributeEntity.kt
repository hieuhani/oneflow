package vn.periscope.adapters.persistence.productattribute

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.ports.product.models.ProductAttributeEntry
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

internal fun ProductAttributeEntity.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[ProductAttributeTable.businessId] = businessId
    it[ProductAttributeTable.productId] = productId
    it[ProductAttributeTable.name] = name
    it[ProductAttributeTable.values] = values.joinToString(SEPARATOR)
    it[ProductAttributeTable.createdAt] = createdAt
    it[ProductAttributeTable.updatedAt] = updatedAt
}

internal fun ProductAttributeEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = ProductAttributeEntity(
    id = resultRow[ProductAttributeTable.id].value,
    businessId = resultRow[ProductAttributeTable.businessId],
    productId = resultRow[ProductAttributeTable.productId],
    name = resultRow[ProductAttributeTable.name],
    values = splitToSet(resultRow[ProductAttributeTable.values]),
    createdAt = resultRow[ProductAttributeTable.createdAt],
    updatedAt = resultRow[ProductAttributeTable.updatedAt]
)

internal fun ProductAttributeEntity.toEntry() = ProductAttributeEntry(
    id,
    businessId,
    productId,
    name,
    values,
    createdAt,
    updatedAt
)