package vn.periscope.adapters.persistence.price.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.adapters.persistence.price.enumerate.PriceListStatus
import vn.periscope.adapters.persistence.price.enumerate.PriceListType
import java.time.Instant

object PriceListTable : LongIdTable("price_list") {
    val businessId = long("business_id")
    val type = enumerationByName("type", 32, PriceListType::class)
    val code = varchar("code", 16)
    val name = varchar("name", 100)
    val currencySymbol = varchar("currency_symbol", 3)
    val currencyIso = varchar("currency_iso", 8)
    val status = enumerationByName("status", 32, PriceListStatus::class)
    val pricePolicyId = long("price_policy_id")
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}