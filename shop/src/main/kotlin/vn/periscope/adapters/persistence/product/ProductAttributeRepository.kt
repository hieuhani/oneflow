package vn.periscope.adapters.persistence.product

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.adapters.persistence.resource.ResourceRepository
import vn.periscope.ports.product.models.ProductAttribute

object ProductAttributeRepository :
    ResourceRepository<ProductAttribute, ProductAttributeEntity, Long, ProductAttributeTable>() {
    override val table = ProductAttributeTable

    private const val SEPARATOR = ","


    override fun fromSqlResultRow(resultRow: ResultRow) = ProductAttributeEntity(
        id = resultRow[ProductAttributeTable.id].value,
        businessId = resultRow[ProductAttributeTable.businessId],
        productId = resultRow[ProductAttributeTable.productId],
        name = resultRow[ProductAttributeTable.name],
        values = convertStringToSet(resultRow[ProductAttributeTable.values]),
        createdAt = resultRow[ProductAttributeTable.createdAt],
        updatedAt = resultRow[ProductAttributeTable.updatedAt],
    )

    override fun toInsertStatement(entry: ProductAttribute): ProductAttributeTable.(InsertStatement<Number>) -> Unit = {
        it[businessId] = entry.businessId
        it[productId] = entry.productId
        it[name] = entry.name
        it[values] = entry.values.joinToString(SEPARATOR)
        it[createdAt] = entry.createdAt
        it[updatedAt] = entry.updatedAt
    }

    fun convertStringToSet(values: String): Set<String> {
        if (values.isBlank()) {
            return emptySet()
        }
        return values.split(SEPARATOR).toSet()
    }

    override fun toUpdateStatement(entry: ProductAttribute): ProductAttributeTable.(UpdateStatement) -> Unit = {
        it[values] = entry.values.joinToString(SEPARATOR)
        it[updatedAt] = entry.updatedAt
    }

    fun getByProductId(productId: Long): List<ProductAttribute> {
       return table.select{ table.productId eq productId}.map {  }
    }
}