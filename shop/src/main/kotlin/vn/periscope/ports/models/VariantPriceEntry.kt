package vn.periscope.ports.models

import java.math.BigDecimal

data class VariantPriceEntry(
    val id: Long,
    val priceListId: Long,
    val price: BigDecimal
)
