package vn.periscope.adapters.persistence.variant

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.ports.variant.models.Variant
import java.time.Instant

data class VariantEntity(
    val id: Long,
    val businessId: Long,
    val productId: Long,
    val name: String,
    val sku: String,
    val barcode: String,
    val qrcode: String,
    val status: VariantStatus,
    val sellable: Boolean,
    val taxable: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    companion object
}

internal fun VariantEntity.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[VariantTable.businessId] = businessId
    it[VariantTable.productId] = productId
    it[VariantTable.name] = name
    it[VariantTable.sku] = sku
    it[VariantTable.barcode] = barcode
    it[VariantTable.qrcode] = qrcode
    it[VariantTable.status] = status
    it[VariantTable.sellable] = sellable
    it[VariantTable.taxable] = taxable
    it[VariantTable.createdAt] = createdAt
    it[VariantTable.updatedAt] = updatedAt
}

internal fun VariantEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = VariantEntity(
    id = resultRow[VariantTable.id].value,
    businessId = resultRow[VariantTable.businessId],
    productId = resultRow[VariantTable.productId],
    name = resultRow[VariantTable.name],
    sku = resultRow[VariantTable.sku],
    barcode = resultRow[VariantTable.barcode],
    qrcode = resultRow[VariantTable.qrcode],
    status = resultRow[VariantTable.status],
    sellable = resultRow[VariantTable.sellable],
    taxable = resultRow[VariantTable.taxable],
    createdAt = resultRow[VariantTable.createdAt],
    updatedAt = resultRow[VariantTable.updatedAt],
)

internal fun VariantEntity.toEntry() = Variant(
    id,
    businessId,
    productId,
    name,
    sku,
    barcode,
    qrcode,
    Variant.VariantStatus.valueOf(status.name),
    sellable,
    taxable,
    listOf(),
    createdAt,
    updatedAt
)