package vn.periscope.adapters.persistence.dao

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import vn.periscope.share.statics.PriceListType
import vn.periscope.share.statics.PriceListStatus
import java.time.Instant
import java.util.*

object PriceListTable : LongIdTable("price_list") {
    val nid = uuid("nid").uniqueIndex().default(UUID.randomUUID())
    val businessId = long("business_id")
    val type = enumerationByName("type", 32, PriceListType::class)
    val name = varchar("name", 100)
    val currencySymbol = varchar("currency_symbol", 3)
    val currencyIso = varchar("currency_iso", 8)
    val status = enumerationByName("status", 32, PriceListStatus::class)
    val pricePolicyId = long("price_policy_id")
    val createdAt = timestamp("created_at").default(Instant.now())
    val updatedAt = timestamp("updated_at").default(Instant.now())
}