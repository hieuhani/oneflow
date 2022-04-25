package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.update
import vn.periscope.adapters.persistence.dao.VariantEntity
import vn.periscope.adapters.persistence.dao.VariantTable
import vn.periscope.ports.product.models.Variant

object VariantRepository {
//    override val table = VariantTable
//
//    override fun fromSqlResultRow(resultRow: ResultRow) = VariantEntity(
//        id = resultRow[VariantTable.id].value,
//        businessId = resultRow[VariantTable.businessId],
//        productId = resultRow[VariantTable.productId],
//        name = resultRow[VariantTable.name],
//        sku = resultRow[VariantTable.sku],
//        barcode = resultRow[VariantTable.barcode],
//        qrcode = resultRow[VariantTable.qrcode],
//        status = resultRow[VariantTable.status],
//        createdAt = resultRow[VariantTable.createdAt],
//        updatedAt = resultRow[VariantTable.updatedAt],
//    )
//
//    override fun toInsertStatement(entry: Variant): VariantTable.(InsertStatement<Number>) -> Unit = {
//        it[businessId] = entry.businessId
//        it[productId] = entry.productId
//        it[name] = entry.name
//        it[sku] = entry.sku
//        it[barcode] = entry.barcode
//        it[qrcode] = entry.qrcode
//        it[status] = VariantStatus.valueOf(entry.status.name)
//        it[createdAt] = entry.createdAt
//        it[updatedAt] = entry.updatedAt
//    }
//
//    override fun toUpdateStatement(entry: Variant): VariantTable.(UpdateStatement) -> Unit = {
//        it[name] = entry.name
//        it[sku] = entry.sku
//        it[barcode] = entry.barcode
//        it[qrcode] = entry.qrcode
//        it[status] = VariantStatus.valueOf(entry.status.name)
//        it[updatedAt] = entry.updatedAt
//    }
}