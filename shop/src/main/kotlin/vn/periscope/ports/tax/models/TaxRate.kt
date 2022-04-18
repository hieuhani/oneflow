package vn.periscope.ports.tax.models

import java.math.BigDecimal

data class TaxRate(
    val id: Long,
    val businessId: Long,
    val taxClass: TaxRateClass,
    val name: String,
    val priority: Int,
    val rate: BigDecimal,
)
