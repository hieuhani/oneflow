package vn.periscope.adapters.persistence.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import vn.periscope.adapters.persistence.dao.ProductEntity
import vn.periscope.adapters.persistence.dao.ProductTable
import vn.periscope.core.domain.Product
import java.time.Instant

object ProductRepository
{
    val table = ProductTable

    fun toInsertStatement(domain: Product): ProductTable.(InsertStatement<Number>) -> Unit = {
        it[nid] = domain.nid
        it[businessId] = domain.businessId
        it[taxonomy] = domain.taxonomy
        it[managementMethodology] = domain.managementMethodology
        it[name] = domain.name
        it[brandId] = domain.brandId
        it[industryId] = domain.industryId
        it[createdAt] = Instant.ofEpochMilli(domain.createdAt.epochSeconds)
        it[updatedAt] = Instant.ofEpochMilli(domain.updatedAt.epochSeconds)
    }

    fun fromSqlResultRow(resultRow: ResultRow):ProductEntity {
        id = resultRow[ContentTable.id].value,
        title = resultRow[ContentTable.title],
        description = resultRow[ContentTable.description],
        userId = resultRow[ContentTable.userId],
        contentTypeId = resultRow[ContentTable.contentTypeId],
    }
}