package vn.periscope.ports.price.models

data class PricePolicy(
    val id: Long,
    val businessId: Long,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
)
