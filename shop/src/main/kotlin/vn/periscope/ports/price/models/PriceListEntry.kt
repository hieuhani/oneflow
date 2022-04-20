package vn.periscope.ports.price.models

import java.time.Instant

data class PriceListEntry(
    val id: Long,
    val businessId: Long,
    val type: Type,
    val code: String,
    val name: String,
    val currencySymbol: String,
    val currencyIso: String,
    val status: Status,
    val pricePolicyId: Long? = null,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    enum class Status {
        ACTIVE,
        INACTIVE
    }

    enum class Type {
        PURCHASE,
        SALES
    }
}