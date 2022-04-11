package vn.periscope.ports.models

import java.time.Instant

data class TaxRateClass(
    val id: Long,
    val businessId: Long,
    val name: String,
    val slug: String,
    val createdAt: Instant,
    val updateAt: Instant,
)
