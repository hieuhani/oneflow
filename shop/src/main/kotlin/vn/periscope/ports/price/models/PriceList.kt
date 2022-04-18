package vn.periscope.ports.price.models

import java.time.Instant

data class PriceList(
    val id: Long,
    val businessId: Long,
    val code: String,
    val name: String,
    val currencySymbol: String,
    val currencyIso: String,
    val status: PriceListStatus,
    val pricePolicyId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    enum class PriceListStatus {
        ACTIVE,
        INACTIVE
    }

    enum class PriceListType {
        PURCHASE,
        SELL
    }
}