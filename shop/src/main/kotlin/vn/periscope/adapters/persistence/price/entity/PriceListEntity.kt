package vn.periscope.adapters.persistence.price.entity

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.adapters.persistence.price.enumerate.PriceListStatus
import vn.periscope.adapters.persistence.price.enumerate.PriceListType
import vn.periscope.adapters.persistence.price.table.PriceListTable
import vn.periscope.ports.price.models.PriceListEntry
import java.time.Instant

data class PriceListEntity(
    val id: Long,
    val businessId: Long,
    val type: PriceListType,
    val code: String,
    val name: String,
    val currencySymbol: String,
    val currencyIso: String,
    val status: PriceListStatus,
    val pricePolicyId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    companion object
}

internal fun PriceListEntity.toCreateSqlStatement(statement: InsertStatement<Number>) = statement.let {
    it[PriceListTable.businessId] = businessId
    it[PriceListTable.type] = PriceListType.valueOf(type.name)
    it[PriceListTable.code] = code
    it[PriceListTable.name] = name
    it[PriceListTable.currencySymbol] = currencySymbol
    it[PriceListTable.currencyIso] = currencyIso
    it[PriceListTable.status] = PriceListStatus.valueOf(status.name)
    it[PriceListTable.pricePolicyId] = pricePolicyId
    it[PriceListTable.createdAt] = createdAt
    it[PriceListTable.updatedAt] = updatedAt
}

internal fun PriceListEntity.Companion.fromSqlResultRow(resultRow: ResultRow) = PriceListEntity(
    id = resultRow[PriceListTable.id].value,
    businessId = resultRow[PriceListTable.businessId],
    type = resultRow[PriceListTable.type],
    code = resultRow[PriceListTable.code],
    name = resultRow[PriceListTable.name],
    currencySymbol = resultRow[PriceListTable.currencySymbol],
    currencyIso = resultRow[PriceListTable.currencyIso],
    status = resultRow[PriceListTable.status],
    pricePolicyId = resultRow[PriceListTable.pricePolicyId],
    createdAt = resultRow[PriceListTable.createdAt],
    updatedAt = resultRow[PriceListTable.updatedAt],
)

internal fun PriceListEntity.toEntry() = PriceListEntry(
    id,
    businessId,
    PriceListEntry.Type.valueOf(type.name),
    code,
    name,
    currencySymbol,
    currencyIso,
    PriceListEntry.Status.valueOf(type.name),
    pricePolicyId,
    createdAt,
    updatedAt
)