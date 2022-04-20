package vn.periscope.adapters.persistence.productattribute

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.adapters.persistence.resource.ResourceRepository
import vn.periscope.ports.product.models.ProductAttributeEntry

object ProductAttributeRepository :
    ResourceRepository<ProductAttributeEntry, ProductAttributeEntity, Long, ProductAttributeTable>() {
    override val table = ProductAttributeTable

    override fun fromSqlResultRow(resultRow: ResultRow) = ProductAttributeEntity(
        id = resultRow[ProductAttributeTable.id].value,
        businessId = resultRow[ProductAttributeTable.businessId],
        productId = resultRow[ProductAttributeTable.productId],
        name = resultRow[ProductAttributeTable.name],
        values = splitToSet(resultRow[ProductAttributeTable.values]),
        createdAt = resultRow[ProductAttributeTable.createdAt],
        updatedAt = resultRow[ProductAttributeTable.updatedAt],
    )

    override fun toInsertStatement(entry: ProductAttributeEntry): ProductAttributeTable.(InsertStatement<Number>) -> Unit = {
        it[businessId] = entry.businessId
        it[productId] = entry.productId
        it[name] = entry.name
        it[values] = entry.values.joinToString(SEPARATOR)
        it[createdAt] = entry.createdAt
        it[updatedAt] = entry.updatedAt
    }

    override fun toUpdateStatement(entry: ProductAttributeEntry): ProductAttributeTable.(UpdateStatement) -> Unit = {
        it[values] = entry.values.joinToString(SEPARATOR)
        it[updatedAt] = entry.updatedAt
    }

    fun getByProductId(productId: Long): List<ProductAttributeEntity> {
        return table.select { ProductAttributeTable.productId eq productId }.map { fromSqlResultRow(it) }
    }
}