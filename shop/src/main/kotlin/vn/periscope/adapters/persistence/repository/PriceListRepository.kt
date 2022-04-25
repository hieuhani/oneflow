package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import vn.periscope.adapters.persistence.entity.PriceListEntity
import vn.periscope.share.statics.PriceListStatus
import vn.periscope.adapters.persistence.entity.PriceListTable
import vn.periscope.share.statics.PriceListType
import vn.periscope.adapters.persistence.resource.ResourceRepository
import vn.periscope.ports.models.PriceListEntry

object PriceListRepository : ResourceRepository<PriceListEntry, PriceListEntity, Long, PriceListTable>() {
    override val table = PriceListTable

    override fun fromSqlResultRow(resultRow: ResultRow) = PriceListEntity(
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

    override fun toInsertStatement(entry: PriceListEntry): PriceListTable.(InsertStatement<Number>) -> Unit = {
        it[businessId] = entry.businessId
        it[type] = PriceListType.valueOf(entry.type.name)
        it[code] = entry.code
        it[name] = entry.name
        it[currencySymbol] = entry.currencySymbol
        it[currencyIso] = entry.currencyIso
        it[status] = PriceListStatus.valueOf(entry.status.name)
        it[pricePolicyId] = entry.pricePolicyId!!
        it[createdAt] = entry.createdAt
        it[updatedAt] = entry.updatedAt

    }

    override fun toUpdateStatement(entry: PriceListEntry): PriceListTable.(UpdateStatement) -> Unit = {
        it[code] = entry.code
        it[name] = entry.name
        it[currencySymbol] = currencySymbol
        it[currencyIso] = currencyIso
        it[status] = PriceListStatus.valueOf(entry.status.name)
        it[pricePolicyId] = pricePolicyId
        it[updatedAt] = entry.updatedAt
    }
}